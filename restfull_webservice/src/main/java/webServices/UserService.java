package webServices;

import beans.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("users")
public class UserService {

    private static ArrayList<User> users;

    public UserService() {
        if (users == null) {
            users = new ArrayList<>();

            users.add(new User(
                    1,
                    "Борис Иванов",
                    10,
                    "Русе, ул. Плиска 10"
            ));

            users.add(new User(
                    2,
                    "Калоян Петров",
                    20,
                    "Варна, ул. Александровска 20"
            ));

            users.add(new User(
                    3,
                    "Вяра Стефанова",
                    30,
                    "Бургас, ул. Стара планина 30"
            ));
        }
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getUserById(@PathParam("id") int id) {

        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        Response.ResponseBuilder rb = Response.status(404);
        return rb.build();
    }
    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") int id) {

        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                break;
            }
        }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User addUser(User newUser) {

        int lastUserIndex = users.size() - 1;
        int nextAvailableID = users.get(lastUserIndex).getId() + 1;

        newUser.setId(nextAvailableID);

        users.add(newUser);

        return newUser;
    }
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object updateUser(@PathParam("id") int id, User newUserData) {

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);

            if (user.getId() == id) {
                newUserData.setId(id);
                users.set(i, newUserData);

                return newUserData;
            }
        }

        Response.ResponseBuilder rb = Response.status(404);
        return rb.build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAllUsers() {
        return users;
    }
}