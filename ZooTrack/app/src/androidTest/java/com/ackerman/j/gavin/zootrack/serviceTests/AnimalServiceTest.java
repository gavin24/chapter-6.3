package com.ackerman.j.gavin.zootrack.serviceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.ackerman.j.gavin.zootrack.Config.DbConstants.GlobalContext;
import com.ackerman.j.gavin.zootrack.Config.Util.App;
import com.ackerman.j.gavin.zootrack.Domain.Animal;
import com.ackerman.j.gavin.zootrack.services.Impl.AnimalServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by gavin.ackerman on 2016-05-11.
 */
public class AnimalServiceTest extends AndroidTestCase
{
    private AnimalServiceImpl animalService;
    private boolean isBound;
   public Animal testanimal = new Animal();
    private Long id;




    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AnimalServiceImpl.AnimalServiceLocalBinder binder
                    = (AnimalServiceImpl.AnimalServiceLocalBinder) service;
            animalService = binder.getService();
            isBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };


    @Override
    public void setUp() throws Exception {
        super.setUp();
     /*  Intent intent = new Intent(this.getContext(), AnimalServiceImpl.class);
       GlobalContext.context = this.getContext();
    //   animalService = AnimalServiceImpl.getInstance();
       GlobalContext.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);*/
        Intent intent = new Intent(App.getAppContext(), AnimalServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

   /* @Test
    public void testDeleteAnimal()
    {
        Animal animal = new Animal.Builder()
                .name("delete")
                .species("pig")
                .age(24)
                .Country("spain")
                .build();
        animal = animalService.addAnimal(animal);
        animalService.deleteAnimal(animal);
        Assert.assertNull(animal.getId());
    }*/
    @Test
    public void testCreateAnimal()
    {
        Animal V = new Animal();
        Animal animal = new Animal.Builder()
                .name("Petes")
                .species("Loskin")
                .age(4)
                .Country("england")
                .build();
        V = animalService.addAnimal(animal);
        Assert.assertNotNull(V);
    }
  /*  @Test
    public void testGetAnimal()
    {

        Assert.assertNotNull(animalService.getAnimal(testanimal.getId()));
    }*/
    @Test
    public void testGetAllAnimals()
    {
        Assert.assertNotNull(animalService.getAllAnimals());
    }
  /*  @Test
    public void testUpdateAnimal()
    {
        Animal animal = new Animal.Builder()
                .name("george")
                .species("bear")
                .age(24)
                .Country("england")
                .build();
        animalService.addAnimal(animal);

        Animal updateEntity = new Animal.Builder()
                .id(animal.getId())
                .copy(animal)
                .Country("mexico")
                .build();
      Animal newA =  animalService.updateAnimal(updateEntity);
        Assert.assertEquals("mexico", newA.getCountry());

    }

    @Test
    public void testDeleteAllAnimals()
    {
        Assert.assertEquals(0,animalService.removeAllAnimals());
    }
*/
}
