FROM postgres:16

COPY dumps/dump_file.tar /tmp/

ENV POSTGRES_DB=smeshtaj_dan_trebit
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=admin
