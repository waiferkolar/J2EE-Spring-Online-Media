package coder.impls;

import coder.daos.PostDao;
import coder.models.Category;
import coder.models.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PostImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addPost(Post post) {
        session().save(post);
        session().flush();
    }

    @Override
    public List<Post> getAllPost() {
        Query query = session().createQuery("from Post");
        List<Post> posts = query.list();
        session().flush();
        return posts;
    }

    @Override
    public Post getPostById(int id) {
        Post post = session().get(Post.class, id);
        session().flush();
        return post;
    }

    @Override
    public void updatePost(Post post) {
        session().saveOrUpdate(post);
        session().flush();
    }

    @Override
    public void deletePost(int id) {
        session().delete(getPostById(id));
        session().flush();
    }


}
