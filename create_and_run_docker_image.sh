
# create image The resulting image will be named catalog-service:0.0.1-SNAPSHOT by default (<project_name>:)
./gradlew bootBuildImage
docker run --rm --name catalog-service -p 8080:8080 catalog-service:0.0.1-SNAPSHOT