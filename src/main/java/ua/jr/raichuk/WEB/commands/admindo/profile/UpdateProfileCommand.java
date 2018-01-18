package ua.jr.raichuk.WEB.commands.admindo.profile;

import ua.jr.raichuk.DB.entities.Entity;
import ua.jr.raichuk.DB.entities.impls.Profile;
import ua.jr.raichuk.Exceptions.DataException;
import ua.jr.raichuk.WEB.commands.FactoryCommand;
import ua.jr.raichuk.WEB.commands.admindo.AdminUpdateCommand;
import ua.jr.raichuk.WEB.validators.ProfileValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * @author Jesus Raichuk
 */
public class UpdateProfileCommand extends AdminUpdateCommand<Profile> {
    @Override
    protected Entity getEntity() {
        return new Profile();
    }

    @Override
    protected String getRedirect() {
        return FactoryCommand.ADMIN_SHOW_LIST_PROFILE;
    }

    @Override
    protected Profile getValidEntity(HttpServletRequest request) throws DataException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("nameP");
        String surname = request.getParameter("surnameP");
        String sex = (String)request.getParameter("sexP");
        Date birthday = Date.valueOf(request.getParameter("birthdayP"));
        Double height = Double.parseDouble(request.getParameter("heightP"));
        Double weight = Double.parseDouble(request.getParameter("weightP"));
        Integer activeTime = Integer.parseInt(request.getParameter("activeTimeP"));

        if(ProfileValidator.isValid(name, surname, sex, birthday, height, weight ,activeTime) &&
                ProfileValidator.isIdPExisting(id)){
            Profile Profile = new Profile(id, name, surname, sex, birthday, height, weight ,activeTime);
            return Profile;
        } else {
            throw new DataException("Data is incorrect");
        }
    }
}