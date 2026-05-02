package webServices;

import beans.Landmark;
import beans.LandmarkResponse;
import beans.LandmarkType;
import beans.Review;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

@Path("landmarks")
public class LandmarkService {
    private static ArrayList<Landmark> landmarks;
    private static ArrayList<Review> reviews;

    public LandmarkService() {
        if (landmarks == null) {
            landmarks = new ArrayList<>();

            landmarks.add(new Landmark(
                    UUID.randomUUID().toString(),
                    "Рилски езера",
                    "Група ледникови езера в Рила планина.",
                    LandmarkType.MOUNTAIN,
                    0.0
            ));

            landmarks.add(new Landmark(
                    UUID.randomUUID().toString(),
                    "Крушунски водопади",
                    "Красиви водопади и екопътека в Северна България.",
                    LandmarkType.WATERFALL,
                    0.0
            ));

            landmarks.add(new Landmark(
                    UUID.randomUUID().toString(),
                    "Деветашка пещера",
                    "Голяма карстова пещера край село Деветаки.",
                    LandmarkType.CAVE,
                    0.0
            ));
        }

        if (reviews == null) {
            reviews = new ArrayList<>();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<LandmarkResponse> getAllLandmarks(@QueryParam("type") LandmarkType type) {
        ArrayList<LandmarkResponse> result = new ArrayList<>();

        for (Landmark landmark : landmarks) {
            if (type == null || landmark.getType() == type) {
                result.add(new LandmarkResponse(
                        landmark.getId(),
                        landmark.getName(),
                        landmark.getType()
                ));
            }
        }

        return result;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getLandmarkById(@PathParam("id") String id) {
        Landmark landmark = findLandmarkById(id);

        if (landmark == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return landmark;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object addLandmark(Landmark newLandmark) {
        if (newLandmark == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Landmark data is required")
                    .build();
        }

        if (newLandmark.getName() == null || newLandmark.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Name is required")
                    .build();
        }

        if (newLandmark.getDescription() == null || newLandmark.getDescription().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Description is required")
                    .build();
        }

        if (newLandmark.getType() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Type is required")
                    .build();
        }

        if (isNameTaken(newLandmark.getName(), null)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Landmark name must be unique")
                    .build();
        }

        newLandmark.setId(UUID.randomUUID().toString());
        newLandmark.setRating(0.0);

        landmarks.add(newLandmark);

        return newLandmark;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object updateLandmark(@PathParam("id") String id, Landmark newData) {
        if (newData == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Landmark data is required")
                    .build();
        }

        for (int i = 0; i < landmarks.size(); i++) {
            Landmark landmark = landmarks.get(i);

            if (landmark.getId().equals(id)) {
                if (newData.getName() == null || newData.getName().trim().isEmpty()) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Name is required")
                            .build();
                }

                if (newData.getDescription() == null || newData.getDescription().trim().isEmpty()) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Description is required")
                            .build();
                }

                if (newData.getType() == null) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Type is required")
                            .build();
                }

                if (isNameTaken(newData.getName(), id)) {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("Landmark name must be unique")
                            .build();
                }

                newData.setId(id);
                newData.setRating(landmark.getRating());

                landmarks.set(i, newData);

                return newData;
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLandmark(@PathParam("id") String id) {
        Landmark landmark = findLandmarkById(id);

        if (landmark == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        landmarks.remove(landmark);

        ArrayList<Review> reviewsToRemove = new ArrayList<>();

        for (Review review : reviews) {
            if (review.getLandmarkId().equals(id)) {
                reviewsToRemove.add(review);
            }
        }

        reviews.removeAll(reviewsToRemove);

        return Response.noContent().build();
    }

    @POST
    @Path("/{id}/reviews")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object addReview(@PathParam("id") String landmarkId, Review review) {
        Landmark landmark = findLandmarkById(landmarkId);

        if (landmark == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (review == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Review data is required")
                    .build();
        }

        if (review.getUsername() == null || review.getUsername().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username is required")
                    .build();
        }

        if (review.getRating() == null || review.getRating() < 1 || review.getRating() > 5) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Rating must be between 1 and 5")
                    .build();
        }

        review.setId(UUID.randomUUID().toString());
        review.setLandmarkId(landmarkId);

        reviews.add(review);
        recalculateRating(landmarkId);

        return review;
    }

    @GET
    @Path("/{id}/reviews")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getReviewsByLandmarkId(@PathParam("id") String landmarkId) {
        Landmark landmark = findLandmarkById(landmarkId);

        if (landmark == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ArrayList<Review> result = new ArrayList<>();

        for (Review review : reviews) {
            if (review.getLandmarkId().equals(landmarkId)) {
                result.add(review);
            }
        }

        return result;
    }

    @GET
    @Path("/top-rated")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Landmark> getTopRatedLandmarks() {
        ArrayList<Landmark> result = new ArrayList<>(landmarks);

        result.sort(Comparator.comparing(Landmark::getRating).reversed());

        if (result.size() > 10) {
            return new ArrayList<>(result.subList(0, 10));
        }

        return result;
    }

    private Landmark findLandmarkById(String id) {
        for (Landmark landmark : landmarks) {
            if (landmark.getId().equals(id)) {
                return landmark;
            }
        }

        return null;
    }

    private boolean isNameTaken(String name, String ignoredId) {
        for (Landmark landmark : landmarks) {
            boolean sameName = landmark.getName().equalsIgnoreCase(name);
            boolean differentLandmark = ignoredId == null || !landmark.getId().equals(ignoredId);

            if (sameName && differentLandmark) {
                return true;
            }
        }

        return false;
    }

    private void recalculateRating(String landmarkId) {
        int sum = 0;
        int count = 0;

        for (Review review : reviews) {
            if (review.getLandmarkId().equals(landmarkId)) {
                sum += review.getRating();
                count++;
            }
        }

        Landmark landmark = findLandmarkById(landmarkId);

        if (landmark != null) {
            if (count == 0) {
                landmark.setRating(0.0);
            } else {
                landmark.setRating((double) sum / count);
            }
        }
    }
}