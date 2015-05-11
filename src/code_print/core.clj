(ns code-print.core
  (:import java.io.File)
  (:require [clojure.java.io :as io]))

;;; utility functions

(defn is-file? [f] (.isFile f))
(defn is-dir?  [f] (.isDirectory f))
(defn get-name [f] (.getName f))
(defn get-extension  [f] (last (.split (get-name f) "\\.")))
(defn dir-empty?     [f] (= (count (.listFiles f)) 0))
(defn append-to-file [f contents] (spit f contents :append true))

(defn make-filename 
  "Give a file an absolute path for its filename (without slashes etc)."
  ([f parent]
   (str 
    (get-name (or parent (.getParentFile f)))
    ".md"))
  ([f]
   (make-filename f f)))

(defn get-language
  "Get the programming language from the file extension. Defaults to the extension if
none is found. This is for the markdown code blocks."
  [f]
  (let [ext (get-extension f) 
        filemap {:clj "clojure" 
                 :js  "javascript" 
                 :py  "python" 
                 :rb  "ruby" 
                 :txt ""}]
    ((keyword ext) filemap ext)))

;;; main program

(defn work-dir 
  "Create the initial file holding the contents of all sub-files of directory [dir]."
  [dir]
  (println (get-name dir))
  (append-to-file (make-filename dir)
                  (str "#" (get-name dir) "\n\n")))

(defn work-file 
  "Append the contents of file [f] to the monolithical directory-representing file."
  [f]
  (println (get-name f))
  (append-to-file (make-filename f nil)
                  (str "##" (get-name f) 
                       "\n\n```" (get-language f) "\n"
                       (slurp f) 
                       "\n```\n\n")))

(defn printerino 
  "Compile all files (including subfiles) in the path [dir] into a (usually smaller) set of markdown files."
  [dir]
  (let [files (rest (file-seq dir))]
    (map #(if (is-dir? %)
            (if (not (dir-empty? %)) 
              (work-dir %))
            (work-file %)) files)))

(defn -main
  "Application entrypoint."
  [arg1 & rest]
  (printerino (io/file arg1)))

