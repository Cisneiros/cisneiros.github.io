FROM theasp/clojurescript-nodejs:shadow-cljs-alpine
WORKDIR /app
COPY package.json package-lock.json shadow-cljs.edn /app/
RUN shadow-cljs npm-deps && npm install --save-dev shadow-cljs
COPY ./ /app/
RUN shadow-cljs info
CMD ["shadow-cljs", "release", "app"]
