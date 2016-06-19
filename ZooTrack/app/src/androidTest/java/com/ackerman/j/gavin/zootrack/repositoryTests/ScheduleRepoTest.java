package com.ackerman.j.gavin.zootrack.repositoryTests;

import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Config.Util.AppUtil;
import com.ackerman.j.gavin.zootrack.Domain.Schedule;
import com.ackerman.j.gavin.zootrack.Domain.Show;
import com.ackerman.j.gavin.zootrack.Repository.Impl.ScheduleRepositoryImpl;
import com.ackerman.j.gavin.zootrack.Repository.ScheduleRepository;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
public class ScheduleRepoTest extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;
    private Date day = new Date(14/06/2014);
    public void testCreateReadUpdateDelete() throws Exception {
        ScheduleRepository repo = new ScheduleRepositoryImpl(this.getContext());
        // CREATE    (Long id,List<Show> show,String type, String duration, String coach
        Show show = new Show.Builder()
                .name("crazy Babbons")
                .day(day)
                .build();

        List<Show> showList = new ArrayList<Show>();
        showList.add(show);
        Schedule createEntity = new Schedule.Builder()
                .type("daily")
                .duration("4 hours")
                .coach("andre")
                .show(showList)
                .build();
        Schedule insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Schedule> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Schedule entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Schedule updateEntity = new Schedule.Builder()
                .copy(entity)
                .coach("john")
                .show(showList)
                .build();
        repo.update(updateEntity);
        Schedule newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","john",newEntity.getCoach());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Schedule deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
