# Blog Platform API Management System

This is a Blog Management System built using Spring Boot. It simulates the basic operations of a Blog Post system, allowing the user to create and manage posts. You can create, read, update, and delete.

The primary functionalities include:

- **Create a Post**: To create a new blog post, navigate to `/posts/new` or click on the "Join the party" button on the home page, or the "Create" button on the posts page. Fill out the form and click "Submit". You will then be redirected to the posts list view.
- **Read (View) Post**: To view all posts, navigate to `/posts`. This will display a list of all blog posts in the system.
- **Update a Post**: To update a post's details, click on the "Edit" button next to the post you want to update in the posts list view. You'll be redirected to a form where you can edit the post's details. Submitting the form will update the post's details and redirect you back to the posts list view.
- **Delete a Post**: To delete a post, click on the "Delete" button next to the post you want to delete in the posts list view. The post will be deleted and you'll remain on the updated posts list view.

## How to run this project

1. Clone the repository: `git clone https://github.com/Deminei/BlogPlatform.git`
2. Move into the project directory: `cd BlogPlatform`
3. Run the application: `./mvnw spring-boot:run` (or on IntelliJ, just run the `BlogPostApplication`)
4. Open your web browser and visit http://localhost:8080 for the home page, http://localhost:8080/posts to view or manage posts.

## Testing the application

To test the application, simply follow the steps described in the functionalities after running the project.

## Added Docker Files!

Dockerized for convenience
To run this project as a Docker image:

1. run `docker build -t my-blog-app .`
2. Use docker to run the image created.

## Happy Blogging!
