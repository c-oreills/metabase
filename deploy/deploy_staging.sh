#!/bin/bash

BASEDIR=$(dirname $0)

if [ -z $1 ]; then
  echo "Oops!  You need to specify the name of the EB app version to deploy."
  exit 1
fi

APP_BUNDLE=$1

ENVIRONMENT=metabase-staging


# upload app version to EB
# TODO: check if version already exists
${BASEDIR}/upload_version.sh ${APP_BUNDLE}

# deploy EB version to environment
${BASEDIR}/deploy_version.sh ${APP_BUNDLE} ${ENVIRONMENT}
