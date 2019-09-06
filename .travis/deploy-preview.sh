#!/usr/bin/env bash

if [ "${TRAVIS_PULL_REQUEST}" != "false" ] ; then
  NOW_URL=$(now out --name cisne-dev --no-clipboard --token ${NOW_TOKEN})

  curl -H "Authorization: token ${GITHUB_BOT_TOKEN}" -X POST \
    -d "{\"body\": \"Preview the last changes (${TRAVIS_PULL_REQUEST_SHA}) at ${NOW_URL}\"}" \
    "https://api.github.com/repos/${TRAVIS_REPO_SLUG}/issues/${TRAVIS_PULL_REQUEST}/comments"
fi
