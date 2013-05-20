(ns com.upgradingdave.properties
  (:require [clojure.java.io :as io]))

(defn from-classpath
  "Find a properties file on the classpath and read it into clojure
  data structures. Convenient for loading config from a file. If
  you're using lein 2, you can control the files available on the
  classpath using :resource-paths (and can also use lein profiles for
  full power)" [file-name]
  (into {} (for [[k v] (doto (java.util.Properties.) 
                         (.load (-> (Thread/currentThread) 
                                    (.getContextClassLoader) 
                                    (.getResourceAsStream file-name))))]
             [(keyword k) (binding [*read-eval* false] (read-string v))])))

(defn from-filepath
  "Find a properties file by file path and read it into clojure data
  structures. FILE-NAME is an absolute or relative path to the
  properties file."
  [file-name]
  (with-open [^java.io.Reader reader (io/reader file-name)] 
    (let [props (java.util.Properties.)]
      (.load props reader)
      (into {} (for [[k v] props] [(keyword k) (binding [*read-eval* false] (read-string v))])))))

