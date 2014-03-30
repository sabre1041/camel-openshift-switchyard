#!/bin/sh
set -e

CAMEL_OPENSHIFT_PRJ_NAME=camel-openshift
DEPENDENCY_DIR=./deps

echo "Removing existing camel-openshift project from deps folder"

rm -rf ${DEPENDENCY_DIR}/${CAMEL_OPENSHIFT_PRJ_NAME}

pushd ${DEPENDENCY_DIR} > /dev/null

git clone https://github.com/sabre1041/camel-openshift.git ${CAMEL_OPENSHIFT_PRJ_NAME}

cd ${CAMEL_OPENSHIFT_PRJ_NAME}

mvn clean install 

popd > /dev/null

echo
echo "openshift-camel dependency successfully installed in local Maven repository"
