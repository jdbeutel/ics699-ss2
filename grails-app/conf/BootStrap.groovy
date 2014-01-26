import com.getsu.wcy.auth.Role
import com.getsu.wcy.auth.User
import com.getsu.wcy.auth.UserRole

class BootStrap {

    def init = { servletContext ->
        def u = new User(username: 'jdb', password: 'password').save()
        def r = new Role(authority: 'ROLE_USER').save()
        UserRole.create(u, r)
    }

    def destroy = {
    }
}
