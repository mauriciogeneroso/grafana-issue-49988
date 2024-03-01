FROM grafana/grafana:10.3.3


RUN grafana-cli plugins install hamedkarbasi93-nodegraphapi-datasource 1.0.1
#RUN grafana-cli plugins update-all

COPY --chown=nobody ./provisioning/ /etc/grafana/provisioning/

USER nobody