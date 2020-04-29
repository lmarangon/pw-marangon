FROM airhacks/glassfish
COPY ./target/pw-marangon.war ${DEPLOYMENT_DIR}
