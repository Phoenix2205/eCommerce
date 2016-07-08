package com.example.sony.ecommerce;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sony.ecommerce.Adapter.ImageSliderAdapter;
import com.example.sony.ecommerce.Database.DatabaseHelper;
import com.example.sony.ecommerce.Model.Product;
import com.example.sony.ecommerce.Model.SingleProductResponse;
import com.example.sony.ecommerce.Service.ServiceGenerator;
import com.example.sony.ecommerce.Service.WooCommerceService;
import com.example.sony.ecommerce.ViewPager.CirclePageIndicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView ivProductDetailPhoto;
    TextView tvCategoryName;
    TextView tvProductName;
    TextView tvPrice;
    TextView tvDescription;
    ImageButton imageButtonFavorite;
    ImageButton imageButtonChat;
    ImageButton imageButtonMore;
    ViewPager viewPagerProductDetail;
    Product product;
    int ID,RowID=1;
    String Price;
    String Normalprice,imgSrc;
    int[] imageList = new int[]{R.drawable.slider2, R.drawable.slider2, R.drawable.slider2};
    private CirclePageIndicator mIndicator;
    Toolbar toolbar;
    FloatingActionButton buyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setContentView(R.layout.activity_product_detail);
        ID=getIntent().getIntExtra("ID",-1);
        imgSrc=getIntent().getStringExtra("Photo");
        Price=getIntent().getStringExtra("Price");
        //Normalprice=getIntent().getStringExtra("NormalPrice");// cai nay lay gia tu ben explore

        Log.d("ID",String.valueOf(ID));
        onMapped();
        getData();
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Product Detail");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (DatabaseHelper.IsDatabaseExist(ProductDetailActivity.this) == false) {
                DatabaseHelper databaseHelper= new DatabaseHelper(ProductDetailActivity.this);
                    int price = 0;
                    price = Integer.parseInt(Price);
                    boolean success = databaseHelper.InsertIntoCartTable( "abc", ID, product.getTitle(), price, 1,imgSrc);
                    RowID++;
                    Log.d("Insert table", String.valueOf(success));
                } else {
                    DatabaseHelper databaseHelper= new DatabaseHelper(ProductDetailActivity.this,DatabaseHelper.DATABASE_NAME);
                    if (databaseHelper.GetDataCartTablebyID(ID)!=-1) {
                        databaseHelper.UpdateQuantityCartTable(ID);
                        Log.d("Update table", String.valueOf(databaseHelper.UpdateQuantityCartTable(ID)));
                    }
                    else {
                        int price=0;
                        price = Integer.parseInt(Price);
                        boolean success = databaseHelper.InsertIntoCartTable("abc", ID, product.getTitle(), price, 1,imgSrc);
                        Log.d("Insert table", String.valueOf(success));
                        RowID++;
                    }

                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onMapped()
    {
        ivProductDetailPhoto= (ImageView) findViewById(R.id.image_view_product_detail);
        tvCategoryName= (TextView) findViewById(R.id.text_view_category_product_detail_name);
        tvProductName=(TextView)findViewById(R.id.text_view_product_detail_name);
        tvPrice=(TextView)findViewById(R.id.text_view_product_detail_price);
        tvDescription=(TextView)findViewById(R.id.text_view_product_detail_description);
        imageButtonChat=(ImageButton)findViewById(R.id.image_button_icon_chat);
        imageButtonFavorite=(ImageButton)findViewById(R.id.image_button_icon_favorite);
        imageButtonMore=(ImageButton)findViewById(R.id.image_button_icon_more);
        viewPagerProductDetail=(ViewPager)findViewById(R.id.view_pager_product_detail);
        buyButton =(FloatingActionButton)findViewById(R.id.fab_product_detail);
        mIndicator=(CirclePageIndicator)findViewById(R.id.indicator_product_detail);
        toolbar=(Toolbar)findViewById(R.id.toolbar_product_detail);

    }

    public  void getData()
    {
        WooCommerceService wooCommerceService= ServiceGenerator.createService(WooCommerceService.class);
        Call<SingleProductResponse> productResponseCall=wooCommerceService.getProductById(ID);
        productResponseCall.enqueue(new Callback<SingleProductResponse>() {
            @Override
            public void onResponse(Call<SingleProductResponse> call, Response<SingleProductResponse> response) {
                SingleProductResponse SingleProductResponse=response.body();
                product=SingleProductResponse.getProduct();
                setView();
            }

            @Override
            public void onFailure(Call<SingleProductResponse> call, Throwable t) {

            }
        });
    }

    private void setView() {
        Glide.with(this).load(product.getFeaturedSrc()).into(ivProductDetailPhoto);
        tvPrice.setText(Price+"$");

        tvCategoryName.setText(product.getCategories().get(0));
        tvProductName.setText(product.getTitle());
        tvDescription.setText(product.getShortDescription());
        viewPagerProductDetail.setAdapter(new ImageSliderAdapter(this, imageList));
        mIndicator.setViewPager(viewPagerProductDetail);

        //Chua implement may cai button like, chat, more va btn mua


    }
}

