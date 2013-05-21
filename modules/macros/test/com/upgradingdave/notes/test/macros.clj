(ns com.upgradingdave.notes.test.macros
  (:use clojure.test
        com.upgradingdave.notes.macros))

(deftest simple-macros
  ;; code is data is code is data ...
  (testing "code is data is code is data ..."

    ;; eval some data ... or is it code? ..
    (is (= 1 (eval 1)))

    ;; quote can be used to prevent evaluation
    (is (= 'y (let [y 10] 'y)))

    ;; We need to qote here to prevent the expression from evaluating
    ;; before it's passed to eval
    (is (= 2 (eval '(+ 1 1))))

    ;; Alternatively, we can build a valid expression first and pass
    ;; that to eval
    (is (= 2 (eval (list (symbol "+") 1 1))))

    ;; Syntax-quote can be used to quote whole expression so you don't
    ;; have to individually quote each
    ;; This will eval to `com.upgradingdave.notes.test.macros/y`
    (is (symbol? (let [y 10] `y)))

    ;; This will eval to `(quote com.upgradingdave.notes.test.macros/y)`
    (is (not (symbol? (let [y 10] ``y))))

    ;; Unquote brings it back to a symbol
    (is (symbol?  (let [y 10] ``~y))))

  ;; Unquote splice does an unquote, then essentially inserts inner
  ;; expression into containing expression    
  (testing "unquote splice"
    ;; quote entire list
    (is (= `(1 2 (list 3 4)) `(1 2 (list 3 4))))
    
    ;; Unquote inside a quote
    (is (= `(1 2 (3 4)) `(1 2 ~(list 3 4))))

    ;; Now let's splice it up a bit ... badum dum ching. 
    (is (= `(1 2 3 4) `(1 2 ~@(list 3 4))))))

(deftest setup-teardown
  (testing "Use setup-teardown macro"

    ;; Example of how to use a macro to setup and teardown
    (is (= ["hello" "how about that?" "goodbye"]
           (with-setup-and-teardown1 #(str "hello") #(str "goodbye") "how about that?")))

    ;; Another setup and teardown example, this time using bindings
    (is (= ["I see you bound: Dave and: blue" "Did some stuff" "goodbye, Dave"]
           (with-setup-and-teardown2 [name "Dave" color "blue"]
             (fn [name color] (str "I see you bound: " name " and: " color))
             (fn [name _] (str "goodbye, " name)) (str "Did some stuff")
             )))

    ;; Similar to the last macro, but this version uses named bindings
    (is (= ["Dave" "Did some stuff" "red"]
           (with-setup-and-teardown3 [name "Dave" color "red"]
             #(str name) #(str color) (str "Did some stuff"))))
    ))
