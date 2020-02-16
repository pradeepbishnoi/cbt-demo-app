(defproject cbt-demo-app "0.1.0-SNAPSHOT"
  :description "Clojre BraveTrue: Demo app to showcase the repl based debugging"
  :url "https://pradeepbishnoi.github.io"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [nrepl "0.8.2"]
                 [compojure "1.6.1"]
                 [http-kit "2.3.0"]
                 [cheshire "5.10.0"]
                 [org.clojure/data.json "1.0.0"]
                 [ring/ring-defaults "0.3.2"]]
  :repl-options {:init-ns cbt-demo-app.core}
  :main ^:skip-aot cbt-demo-app.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
            :reveal
             {:dependencies [[vlaaad/reveal "1.3.194"]]
              :repl-options {:nrepl-middleware [vlaaad.reveal.nrepl/middleware]}}})