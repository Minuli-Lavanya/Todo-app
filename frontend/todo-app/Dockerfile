#Use Node.js to build Angular
FROM node:18 AS build

# Set working directory
WORKDIR /app

# Copy package.json and install dependencies
COPY package.json package-lock.json ./
RUN npm install

# Copy the rest of the app and build it
COPY . .
RUN npm run build -- --configuration=production


# Use Nginx to serve the Angular app
FROM nginx:latest
COPY --from=build /app/dist/todo-app/browser /usr/share/nginx/html
EXPOSE 4000
CMD ["nginx", "-g", "daemon off;"]
