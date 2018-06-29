package io.lenar.examples.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyLogSpringIT {

    @Autowired
    Universe universe;

    @Test
    public void bigBangTest() {
        Assert.assertNotNull(universe);
        universe.bigBang(3, 3);
        Assert.assertTrue(universe.getBlackHoles().size() == 3);
        Assert.assertTrue(universe.getStars().size() == 3);
    }

    @Test
    public void bigBangTwiceTest() {
        universe.bigBang(4, 4);
        universe.bigBang(2, 2);
        Assert.assertNotNull(universe);
        Assert.assertTrue(universe.getBlackHoles().size() == 2);
        Assert.assertTrue(universe.getStars().size() == 2);
    }

}
