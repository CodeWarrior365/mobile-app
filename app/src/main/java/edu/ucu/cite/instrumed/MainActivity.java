package edu.ucu.cite.instrumed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle mtoggle=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout =(DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navigationdrawer);

        navigationView.bringToFront();
        mtoggle =new ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.home);

    }
    public void surgicalpag (View view)
    {
        Intent a=new Intent(this,surgicalpage.class);
        startActivity(a);
    }
    public void opthalmologypag (View view)
    {
        Intent a=new Intent(this,opthalmologypage.class);
        startActivity(a);
    }
    public void medicalpag (View view)
    {
        Intent a=new Intent(this,medicalpage.class);
        startActivity(a);
    }
    public void obspag (View view)
    {
        Intent a=new Intent(this,obspage.class);
        startActivity(a);
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mtoggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.home:

                break;
            case R.id.share:
                try {
                    Intent rateintent =new Intent(Intent.ACTION_SEND);
                    rateintent.setType("text/plain");
                    rateintent.putExtra(Intent.EXTRA_SUBJECT,"ElemixAR");
                    rateintent.putExtra(Intent.EXTRA_TEXT,"http://play.google.com/store/apps/details?id=" + getPackageName());
                    startActivity(Intent.createChooser(rateintent,"Share With"));


                }catch (Exception e) {
                    Toast.makeText(this,"Unable to share this app.",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rate:
                Uri uri =Uri.parse("http://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                Intent i =new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(this,"Enable to open\n"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.about:
                startActivity(new Intent(MainActivity.this,About.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
