(ns metabase.query-processor.middleware.add-calculated-hash
  (:require [metabase.query-processor.util :as qputil]))

(defn- add-calculated-hash* [{query-type :type, :as query}]
  (->
   query
   (assoc-in [:info :query-hash] (qputil/query-hash query))
   (assoc-in [:info :query-type] (if qputil/mbql-query?
                                   "MBQL"
                                   "native"))))

(defn add-calculated-hash
  "Middleware that adds `info.query-hash` and `info.query-type` to a query. This is used primarily for userland queries,
  to record QueryExecutions, and in the remarks added to userland queries."
  [qp]
  (comp qp add-calculated-hash*))
