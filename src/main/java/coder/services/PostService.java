package coder.services;

import coder.daos.PostDao;
import coder.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostDao postDao;

    public void addPost(Post post) {
        postDao.addPost(post);
    }

    public List<Post> getAllPosts() {
        return postDao.getAllPost();
    }

    public Post getPostById(int id) {
        return postDao.getPostById(id);
    }

    public void updatePost(Post post) {
        postDao.updatePost(post);
    }

    public void deletePost(int id) {
        postDao.deletePost(id);
    }
}
