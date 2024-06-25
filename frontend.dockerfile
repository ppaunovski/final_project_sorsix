FROM node:18 AS builder

WORKDIR /app

RUN npm install -g @angular/cli

COPY Frontend/package*.json ./
RUN npm install

COPY ./Frontend/ .

RUN npm run build --prod

FROM nginx:alpine

COPY --from=builder /app/dist/* /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]