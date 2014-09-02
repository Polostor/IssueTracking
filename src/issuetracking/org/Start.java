package issuetracking.org;

import issuetracking.org.dao.UserDAO;
import issuetracking.org.model.User;
import java.util.List;
import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

public class Start {

    public static void main(String[] args) throws Exception {

//        final Properties p = new Properties();
//        p.put("IssueTrackingPU", "new://Resource?type=DataSource");
//        p.put("IssueTrackingPU.JdbcDriver", "com.mysql.jdbc.Driver");
//        p.put("IssueTrackingPU.JdbcUrl", "jdbc:mysql://localhost:3306/issuetrack");
//
//        EJBContainer container = EJBContainer.createEJBContainer(p);
//        final Context context = container.getContext();
//
//        UserDAO users = (UserDAO) context.lookup("java:global/injection-of-entitymanager/Movies");
//        users.create(new User("Peta", "pass"));
//        
//        List<User> list = users.findAll();
//        
//        for (User user : list) {
//            users.delete(user);
//        }
    }
}
