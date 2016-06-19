package com.ackerman.j.gavin.zootrack.repositoryTests;

import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.Domain.Food;
import com.ackerman.j.gavin.zootrack.Repository.AnimalRepository;
import com.ackerman.j.gavin.zootrack.Repository.Impl.AnimalRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-04-24.
 */
public class AnimalRepoTest extends AndroidTestCase {
    private static final String TAG="Animal TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        AnimalRepository repo = new AnimalRepositoryImpl(this.getContext());
        // CREATE
        Food food = new Food.Builder()
                .name("steak")
                .stock(3)
                .type("beef")
                .price(345)
                .build();
        Animal createEntity = new Animal.Builder()
                .name("Johny")
                .species("bear")
                .age(24)
                .Country("england")
               // .food(food)
                .build();
        Animal createEntity2 = new Animal.Builder()
                .name("peter2")
                .species("anjo")
                .age(53)
                .Country("portugal")
               // .food(food)
                .build();
        Animal createEntity3 = new Animal.Builder()
                .name("steve3")
                .species("rox")
                .age(14)
                .Country("spain")
                //.food(food)
                .build();
        Animal insertedEntity = repo.save(createEntity);
    //    Animal insertedEntity2 = repo.save(createEntity2);
      //  Animal insertedEntity3 = repo.save(createEntity3);

        id=insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

      /*  //READ ALL
        Set<Animal> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Animal entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Animal updateEntity = new Animal.Builder()
                .id(entity.getId())
                .copy(entity)
                .Country("USA")
                .build();
        repo.update(updateEntity);
        Animal newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","USA",newEntity.getCountry());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Animal deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
*/
    }
}
