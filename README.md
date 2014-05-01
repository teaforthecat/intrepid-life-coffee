# intrepid-life-coffee

A business purpose site for advertising the coffee shop.
The art can be edited to reflect the current art pieces in the shop.

## Historical Setup Steps for Learning Clojure Development

$ lein new reloaded intrepid-life-coffee
$ lein repl
user> (go)


## Config
create config/system.edn
requires :db/:uri and :zookeeper/:config
example content:

{ :db
 { :uri "datomic:riak://localhost:8087/intrepid-life-coffee/intrepid-life-coffee"}
 :zookeeper
 { :config "127.0.0.1:2181"}}


## Usage

$ lein repl
user> (go) ;or (init) ;; create the bucket and add zookeeper config

- start the transactor
/full/path/to/datomic-pro-0.9.4714/bin/transactor `pwd`/config/riak.dev.properties

$ lein run #doesn't do anything yet

## Copyright and License

Copyright © 2014 chris
