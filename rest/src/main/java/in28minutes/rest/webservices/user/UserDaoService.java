package in28minutes.rest.webservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	public static List<User> users = new ArrayList<>();
	static {
		users.add(new User(10, "Akash", LocalDate.of(1991, 8, 14)));
		users.add(new User(12, "Puja", LocalDate.of(1992, 12, 5)));
		users.add(new User(11, "Gaurav", LocalDate.of(1993, 8, 7)));
	}

	public List<User> fetchAllUser() {
		return users;
	}

	public User fetchSpecificUserBasedOnId(int id) {
		Optional<User> optionalUser= users.stream().filter(us -> us.getId() == id).findFirst();
		User specificUser=null;
		if(optionalUser.isPresent()) {
			specificUser = optionalUser.get();
		}else {
			throw new UserNotFoundException(String.format("id %s is not present", id));
		}
		return specificUser;
	}

	public User saveUser(User user) {
		int maxUserById=0;
		if (user.getId() == 0) { // calculating id based on the max id present for the user
			Optional<User> optionalUser = users.stream().max((us1, us2) -> {
				if (us1.getId() > us2.getId()) {
					return 1;
				} else if (us1.getId() < us2.getId()) {
					return -1;
				}
				return 0;
			});
			if(optionalUser.isPresent()) {
				maxUserById = optionalUser.get().getId();
			}
			user.setId(maxUserById+1);
		}
		users.add(new User(user.getId(), user.getName(), user.getDate()));
		return user;
	}
	public User deleteUser(int id) {
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.getId()==id) {
				itr.remove();
				return user;
			}
		}
		return null;
	}
}
