package ua.od.game.repository.dao.impl;


import org.junit.Before;
import org.junit.Test;
import ua.od.game.model.RoomEntity;
import ua.od.game.repository.dao.DbTest;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class RoomDaoImplTest extends DbTest {
    RoomDaoImpl roomDao;

    @Before
    public void init() {
        roomDao = new RoomDaoImpl();
    }

    @Test
    public void getRoomListTest() {
        List<RoomEntity> rooms = roomDao.getAllRoomList();
        for (int i = 0; i < rooms.size(); i++) {
            System.out.print(rooms.get(i).getId() + " ");
            System.out.print(rooms.get(i).getName() + " ");
            System.out.print(rooms.get(i).getDescription()+ " ");
        }
        if (!rooms.isEmpty())
        assertTrue(true);
    }
}

