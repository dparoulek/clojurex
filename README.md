# clojurex

Some useful clojure utility functions, but mostly snippets and
experiments with clojure. 

For more info see: http://dparoulek.github.io/clojurex

# Developer Notes

This project requires a few lein plugins. Add them to
~/.lein/project.clj file like so:

    {:user {:plugins [[lein-marginalia "0.7.1"]
                      [lein-sub "0.2.4"]]}}

To rebuild documentation: 

    git checkout master
    cd modules/<whichever>
    lein marg -d . -f index.html.new src/* test/*
    git checkout gh-pages
    mv index.html.new <whichever>.html

Then, commit and push latest to gh-pages branch

## License

Copyright © 2013 Dave Paroulek, Preferred Version LLC

Distributed under the Eclipse Public License, the same as Clojure.
