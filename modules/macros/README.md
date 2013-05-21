# macros

I always forget the syntax for macros so here are some examples as a
quick reminder.

For more info see: http://dparoulek.github.io/clojurex/macros.html

# Developer Notes

To rebuild documentation: 

    git checkout master
    cd modules/macros
    lein marg -d . -f index.html.new src/* test/*
    git checkout gh-pages
    mv index.html.new macros.html
    
Then, commit and push latest to gh-pages branch
