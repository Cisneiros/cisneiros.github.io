FROM theasp/clojurescript-nodejs:shadow-cljs AS base
WORKDIR /download
RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN dpkg -i ./google-chrome-stable_current_amd64.deb; apt-get -fy install
WORKDIR /app

FROM base AS deps
WORKDIR /app
COPY package.json package-lock.json shadow-cljs.edn /app/
CMD shadow-cljs npm-deps && npm install --save-dev shadow-cljs && shadow-cljs info

FROM base AS base_deps
COPY . /app
COPY .cache/m2 /root/.m2
COPY .cache/node_modules /app/node_modules

FROM base_deps AS test
WORKDIR /app
RUN shadow-cljs compile test
CMD ["./node_modules/karma/bin/karma", "start", "--single-run"]

FROM base_deps AS build
WORKDIR /app
CMD ["shadow-cljs", "release", "app"]
