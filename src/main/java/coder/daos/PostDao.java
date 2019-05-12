package coder.daos;

import coder.models.Category;
import coder.models.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostDao {
    void addPost(Post post);

    List<Post> getAllPost();

    Post getPostById(int id);

    void updatePost(Post post);

    void deletePost(int id);

}
