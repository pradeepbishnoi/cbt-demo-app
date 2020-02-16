# cbt-demo-app

A Clojure library designed to ... well, that part is up to you.

## Usage

To demo the debugging a running application via REPL. 
- You can build you app with `lein uberjar` and then start the nrepl by calling the `http://localhost:port/clj/nrepl`. 
- Connect to REPL using the code editor (like emacs, vim, vscode, atom etc)
- Switch the namespace to one where you want to peek into. Example to switch to core namespace by using `ns cbt-demo-app.core`
- If started this code with `lien repl` or directly using editor to launch repl, to bring the webserver up - you should evaluate `(-main)`

## License

Copyright © 2021 Pradeep Bishnoi

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
