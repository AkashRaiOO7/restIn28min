package in28minutes.rest.webservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	@Autowired
	UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> fetchAllUsers() {
		return userDaoService.fetchAllUser();
	}

	@GetMapping("/users/{id}")
	public User fetchSpecificUser(@PathVariable(required = true) int id) {
		return userDaoService.fetchSpecificUserBasedOnId(id);
	}

	@PostMapping("/users")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		User user = userDaoService.deleteUser(id);
		if (user == null) {
			throw new UserNotFoundException(String.format("id %d not deleted", id));
		}
		return new ResponseEntity<>(new String(
				"User Deleted/actual code should be No_Content, for demo only we are sending the status code OK"),
				HttpStatus.OK);
	}
}
