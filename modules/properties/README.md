# properties

Helper functions for reading property files from clojure. 

For more info see: http://dparoulek.github.io/clojurex/properties.html

# Developer Notes

To generate the docs: 

    git checkout master
    cd modules/properties
    lein marg -d . -f index.html.new src/* test/*
    git checkout gh-pages
    mv index.html.new properties.html

## License

Copyright © 2013 Dave Paroulek, Preferred Version LLC

Distributed under the Eclipse Public License, the same as Clojure.
