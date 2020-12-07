package edu.ucu.cite.instrumed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class obspage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle mtoggle=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obspage);

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
                Intent intent=new Intent(obspage.this,MainActivity.class);
                startActivity(intent);
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
                Intent aboutintent=new Intent(obspage.this,About.class);
                startActivity(aboutintent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}