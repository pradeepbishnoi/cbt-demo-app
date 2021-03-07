(ns cbt-demo-app.core
  (:require [nrepl.server :refer [start-server stop-server]]
            [compojure.core :refer [defroutes GET]]
            [clojure.data.json :as json]
            [org.httpkit.server :as server]
            [clojure.string :as str]
            [compojure.route :as route]
            [cheshire.core :refer [generate-string parse-string]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:gen-class))

(defonce server-status (atom nil))

(def global-debug (atom nil))

(def debug-tap (atom nil))

(def n-server (atom nil))

(def whole-number (range 0 10))

(defn add []
  (+ 20 9))

(defn even-numbs [whole-number]
  (->> whole-number
       (filter even?)
       (map #(* 10 %))
       #_(map #(prn "11 " %))
       doall))

(even-numbs whole-number)

(defn foo [any_name]
  ;;  (reset! global-debug any_name)  ;; for repl mode
   (reset! global-debug {:debug-data any_name :method 'foo})  ;; preferred
   {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str "Hello Clojure!! " (add) " Yes, this demo was very helpful !! So this is working?")})

(defn foo-improved [any_name] ;; fix this by handling an argument
  (let [request_payload any_name]
    ;; (add-tap println)  ;; for sysout mode (with lein)
    ;; (tap> (get-in request_payload [:uri]))
    ;; (prn (str (get-in request_payload [:uri])))
    ;; (reset! global-debug any_name)  ;; for repl mode
    (reset! global-debug {:debug-data any_name :method 'foo-improved})  ;; preferred
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body    (str (get-in request_payload [:uri]))}))

(defn foo-lazy [_]
  (str (even-numbs whole-number)))

(defn foo-fit [_]
  (str (list 1 2 3 4 5)))


(defn start-nrepl ([_]
  (let [_ (println "Starting nrepl")
        port 7888
        host "0.0.0.0"
        _n (start-server :bind "0.0.0.0" :port 7888)]
    (println "nrepl server start " _n)
    (reset! n-server _n)
    {:status 200
     :headers {"Content-Type" "application/json" "Accept" "application/json"}
     :body {"message" (str "nREPL started http://" host ":" port "/")}})))

(defn stop-nrepl [_]
  (let [_ (println "Stopping nrepl")
        port 7888
        host "0.0.0.0"
        _n (stop-server @n-server)]
    (println "nrepl server stopped " _n)
    (reset! n-server nil)
    {:status 200
     :headers {"Content-Type" "application/json" "Accept" "application/json"}
     :body {"message" (str "nREPL was running at http://" host ":" port "/ is now stopped")}}))

(defroutes app-routes
  (GET "/" [] foo)
  (GET "/demo/:some_input" [] foo-improved)
  (GET "/fit/:some_input" [] foo-fit)
  (GET "/lazy/:some_input" [] foo-lazy)
  (GET "/clj/nrepl" [] start-nrepl)
  (GET "/clj/nrepl/stop" [] stop-nrepl)
  (route/not-found "Error, page not found!"))

(defn -main
  "This is our main entry point"
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3001"))]
    ; Run the server with Ring.defaults middleware
    (reset! server-status (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port}))
    (println (str "Running webserver with " args " at http://127.0.0.1:" port "/"))))

(comment 
  (-main))


(comment
  (@server-status) ;; immediate stop
  (@server-status :timeout 100) ;; stop after X milliseconds
   ,)