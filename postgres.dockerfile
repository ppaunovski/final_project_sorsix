# Use the official PostgreSQL image from Docker Hub
FROM postgres:latest

# Copy the SQL dump file into the Docker container (if needed)
COPY dumps/dump_file.tar /tmp/

# Environment variables (customize as needed)
ENV POSTGRES_DB=smeshtaj_dan_trebit
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=admin

# Restore the database dump on container startup
