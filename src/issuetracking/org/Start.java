package issuetracking.org;

import issuetracking.org.data.HibernateUtil;
import org.hibernate.Session;

public class Start {

    private static Session session;

    public static void main(String[] args) {
        setSession();
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession() {
        Session session = HibernateUtil.getSessionFactory().openSession();
    }
}
