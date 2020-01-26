import hibernate.util.HibernateUtil;
import model.User;
import services.TweetManagementService;
import services.UserManagementService;
import services.impl.TweetManagementServiceImpl;
import services.impl.UserManagementServiceImpl;

public class Start {
    public static void main(String[] args) {
        HibernateUtil instance = HibernateUtil.getInstance();
//        TweetManagementService tweetManagementService = new TweetManagementServiceImpl();
//        UserManagementService userManagementService = new UserManagementServiceImpl();
//
//        User newUser = new User.Builder()
//                .login("demo")
//                .name("Jan")
//                .lastName("Kowalski")
//                .email("email@wp.pl")
//                .password("1234").build();
//
//        User newUser2 = new User.Builder()
//                .login("demo2")
//                .name("Tomasz")
//                .lastName("Kowalski")
//                .email("email@onet.pl")
//                .password("qwerty").build();
//
//        User newUser3 = new User.Builder()
//                .login("demo3")
//                .name("Tomasz")
//                .lastName("Kowalski")
//                .email("email@onet.pl")
//                .password("qwerty").build();
//
//        userManagementService.saveUser(newUser);
//        userManagementService.saveUser(newUser2);
//        userManagementService.saveUser(newUser3);
//
//
//        userManagementService.follow(newUser.getLogin(),newUser2.getLogin());
//        userManagementService.follow(newUser.getLogin(),newUser3.getLogin());
//
//        tweetManagementService.addTweet(newUser.getLogin(),"Tweet Tweet 2");
//        tweetManagementService.addTweet(newUser2.getLogin(),"Tweet Tweet by user 2");
//        tweetManagementService.addTweet(newUser3.getLogin(),"Tweet Tweet by user 3");


    }
}
