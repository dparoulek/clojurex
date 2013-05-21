(ns com.upgradingdave.notes.macros
  (use [clojure.tools.macro]))

;; At first glance, macros are the same as functions?!
(defmacro macro1 [x] x)

(defn fun1 [x] x)

;; But look deeper and see that macros evaluate things at compile time
;; AND at runtime, while functions only evaluate at runtime

;; macros transform expressions into code during compile time, and
;; then run that code during runtime

;; In this case, the result of
;; expanding the macro is the symbol x. By itself, the symbol x has no
;; meaning so this throws exception
(defmacro macro2 [x] 'x)

;; Functions transform expressions into values during runtime, so
;; this always results in the symbol x (which doesn't get evaluated
;; like in the macro above)
(defn fun2 [x] 'x)


(defmacro with-setup-and-teardown1
  "Example of how to use macro to preform some setup, run some code,
  and then do tear down." [setup-fn teardown-fn & body]
  `(try
     [(~setup-fn)
      (do ~@body)
      (~teardown-fn)]))

(defn every-nth
  "Take a coll, and return every nth value"
  [n coll]
  (map #(first %) (partition n coll)))

(defmacro with-setup-and-teardown2
  "If we want to be able to affect the code inside the body, then we
 need bindings."
  [bindings setup-fn teardown-fn & body]
  `(let ~bindings
     (try
       [(~setup-fn ~@(every-nth 2 bindings))
        (do ~@body)
        (~teardown-fn ~@(every-nth 2 bindings))])))

(defmacro with-setup-and-teardown3
  "In version 2 of this macro, it's kind of a pain that the setup-fn's
 and teardown-fn's need to have same number of args as the bindings.
 Here's a better version that expects the teardown and startup to call
 bound vars directly." [bindings setup-fn teardown-fn & body]
  `(let ~bindings
     (try
       [(~setup-fn)
        (do ~@body)
        (~teardown-fn)])))
