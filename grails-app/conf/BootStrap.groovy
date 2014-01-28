import com.getsu.wcy.auth.Role
import com.getsu.wcy.auth.User
import com.getsu.wcy.auth.UserRole

class BootStrap {

    def init = { servletContext ->
        def jdb = new User(username: 'jdb@getsu.com', password: 'password').save()
        def admin = new Role(authority: 'ROLE_ADMIN').save()
        UserRole.create(jdb, admin)
        def user = new Role(authority: 'ROLE_USER').save()
        UserRole.create(jdb, user)
        def fab = new User(username: 'fab@getsu.com', password: 'password').save()
        UserRole.create(fab, user)
    }

    def destroy = {
    }
}
