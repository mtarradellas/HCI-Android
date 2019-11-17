rm -rf tmp api
mkdir tmp
echo Creating tmp directory..
echo unzipping api v1.5.0 into tmp
unzip api150.zip -d tmp
mkdir api
echo Creating api directory..
cp tmp/package.json api
echo Copying package.json
cd api
echo Installing node app
npm install
cd ..
echo Copying old directories into api
cp -r tmp/actions tmp/config tmp/db tmp/initializers tmp/locales tmp/node_modules tmp/public api
cd api
./node_modules/.bin/actionhero link --name=ah-swagger-plugin
cd ..
echo Unzipping new version into api
unzip -o api151.zip -d api 
rm -rf tmp
