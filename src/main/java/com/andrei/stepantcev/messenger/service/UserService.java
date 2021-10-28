package com.andrei.stepantcev.messenger.service;

import com.andrei.stepantcev.messenger.model.Profile;
import com.andrei.stepantcev.messenger.model.UsersListRow;
import java.util.List;

public interface UserService {

    boolean checkUserExists(String userLogin);

    Profile getProfile(String userLogin);

    List<UsersListRow> getFriends(String userLogin);

    List<UsersListRow> searchNewFriends(String userLogin, String searchFirstname, String searchLastname);

    void addFriend(String userLogin, Integer friendId);

}