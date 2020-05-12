<?php
	include 'public/inc/header.php';
  if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $tukhoa = $_GET['tukhoa'];
    $search_page = $product->search_page($tukhoa,$pd_by_page,$each_page);
  }
  $all_product = $product->count_product_by_search($tukhoa);
  $result = $all_product->fetch_assoc();
  $product_count = $result['count_search'];
  $total_pages = ceil($product_count/$pd_by_page);
?>

<div class="cl-main-home_page">
	<div class="content">
  <div class="cl-related-product cl-featured-product">
    <div class="container">
      <div class="row">
        <div class="cl-section-related-title col-lg-12" style="margin-bottom: 30px;">
          <h3><?= $product_count ?> results for "<?php echo $tukhoa ?>"</h3>
          <span class="cl-line"></span>
        </div>
        <div class="cl-product-wrapper col-sm-12">
          <div class="cl-product-listing row">
            <?php
              if ($search_page) {
                while ($result = $search_page->fetch_assoc()) {
                  ?>
                    <div class="col-md-3 col-sm-6 cl-col-xs-6 cl-card-product cl-show-card">
                      <div class="cl-product-thumb cl-item-thumb">
                        <a href="Detail_Product.php?proid=<?php echo $result['productId'] ?>&idcat=<?php echo $result['catId'] ?>" class="cl-back-image">
                          <img class="cl-pic-1" src="admin/upload/<?php echo $result['image']; ?>" alt="">
                            <?php $pdid = $result['productId'] ?>

                            <?php
                                $get_one_gallery_byId = $product->get_one_gallery_byId($pdid);
                                if ($get_one_gallery_byId) {
                                    while ($result_gallery = $get_one_gallery_byId->fetch_assoc()) {
                            ?>
                              <img class="cl-pic-2" src="admin/gallery/<?php echo $result_gallery['image']; ?>" alt="">
                            <?php
                                    }
                                }
                            ?>
                        </a>
                        <div class="cl-social">
                          <a class="cl-tooltip_left" onclick="openQuickShop(this);" href="javascript:void(0);" data-cl-tooltip="Descriptive"><i class="fa fa-info" ></i></a>
                          <a class="cl-tooltip_left" href="Detail_Product.php?proid=<?php echo $result['productId'] ?>&idcat=<?php echo $result['catId'] ?>" data-cl-tooltip="Product Detail"><i class="fa fa-eye" ></i></a>
                        </div>
                        <div class="quick-shop-container">
                          <div class="quick-shop-hook">
                            <div class="quick-shop">
                              <div class="content">
                                <div class="close-btn">
                                  <div onclick="closeQuickShop(this);" class="cl-tooltip_left box" data-cl-tooltip="Close">
                                    <i class="washabi-multiply"></i>
                                  </div>
                                </div>
                                <div class="color-picked">
                                  <?php echo $result['product_desc'] ?>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <?php
                          if ($result['quantity_pro']<=0) {
                            ?>
                              <span class="cl-text_note cl-sold_out">Sold Out</span>
                            <?php
                          }else {
                            if ($result['promotion']<=0 || $result['promotion']=='') {
                              echo '';
                            }else {
                              ?>
                                <span class="cl-text_note cl-sale">Sale <?php echo $result['promotion']; ?> %</span>
                              <?php
                            }
                          }
                        ?>
                      </div>
                      <div class="cl-product-info">
                        <h2 class="cl-product-name">
                        <a href="Detail_Product.php?proid=<?php echo $result['productId'] ?>&idcat=<?php echo $result['catId'] ?>" title=""><?php echo $result['productName']; ?></a>
                        </h2>
                        <div class="cl-clearfix"></div>
                       <?php
                          if ($result['quantity_pro'] <= 0) {
                            ?>
                               <div class="cl-out_stock_pr">
                                  <span>Out of Stock</span>
                               </div>
                            <?php
                          }else {
                            ?>
                               <span class="cl-price">
                                   <?php
                                      if ($result['promotion']=="" || $result['promotion']==0){
                                        echo '';
                                      }else {
                                        ?>
                                        <ins class="cl-ProductPrice" style="color: #cf0f0f">
                                           <span class="cl-money">
                                                <?php
                                                    $compare_price = ($result['price'])-($result['price']*$result['promotion']/100);
                                                  echo number_format($compare_price).' '."VND";
                                                ?>
                                           </span>
                                        </ins>
                                        <?php
                                      }
                                    ?>
                                   <?php
                                      if ($result['promotion']=="" || $result['promotion']==0) {
                                        ?>
                                            <ins class="cl-ProductPrice" >
                                                <span class="cl-money"><?php echo number_format($result['price'])." "."VND"; ?></span>
                                            </ins>
                                        <?php
                                      }else {
                                        ?>
                                           <del class="cl-ProductPrice">
                                                <span class="cl-money"><?php echo number_format($result['price'])." "."VND"; ?></span>
                                           </del>
                                       <?php
                                      }
                                      ?>
                               </span>
                           <?php
                          }
                        ?>
                      </div>
                    </div>
                  <?php
                }
              }else {
                ?>
                  <div class="cl-no-result col-12">
                      <span>Your search did not yield any results.</span>
                  </div>
                <?php
              }
            ?>
            <div class="cl-pagination cl-mrt-20 col-lg-12">
              <ul class="cl-content-pagination">
                      <?php
                        if($product_count <= $pd_by_page){
                          echo '';
                        }else {
                          if ($page > 1) {
                            ?>
                              <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page= <?php echo '1' ?>" title=""  class="cl-square square-right"><i class="fa fa-angle-left"></i><i class="fa fa-angle-left"></i></a></li>
                              <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo ($page-1) ?>" title=""  class="cl-square square-right"><i class="fa fa-angle-left"></i></a></li>
                            <?php
                          }

                          if ($total_pages <= 9) {
                            for($i=1; $i <= $total_pages; $i++){
                                if ($page == $i) {
                                  ?>
                                    <li><a  title="" class="<?php if($page == $i) echo "cl-number active"; ?>"><?php echo $i ?></a></li>
                                  <?php
                                }else {
                                  ?>
                                    <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo $i ?>" title="" ><?php echo $i ?></a></li>
                                  <?php
                                }
                            }
                          }
                          else if($total_pages > 9){
                            if($page <= 4) {
                                for($i=1; $i < 6; $i++){
                                  if ($page == $i) {
                                    ?>
                                      <li><a  title="" class="<?php if($page == $i) echo "cl-number active"; ?>"><?php echo $i ?></a></li>
                                    <?php
                                  }else {
                                    ?>
                                      <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo $i ?>" title="" ><?php echo $i ?></a></li>
                                    <?php
                                  }
                                }
                              ?>
                                <li><a>...</a></li>
                                <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo ($total_pages-1) ?>"><?php echo ($total_pages-1) ?></a></li>
                                <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo ($total_pages) ?>"><?php echo $total_pages ?></a></li>
                              <?php
                            }
                            elseif ($page > 4 && $page < $total_pages - 4) {
                              ?>
                                <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=1">1</a></li>
                                <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=2">2</a></li>
                                <li><a>...</a></li>
                              <?php

                              for($i = $page - 1; $i <= $page +1; $i++){
                                if ($page == $i) {
                                  ?>
                                    <li><a  title="" class="<?php if($page == $i) echo "cl-number active"; ?>"><?php echo $i ?></a></li>
                                  <?php
                                }else {
                                  ?>
                                    <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo $i ?>" title="" ><?php echo $i ?></a></li>
                                  <?php
                                }
                              }

                              ?>
                                <li><a>...</a></li>
                                <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo ($total_pages-1) ?>"><?php echo ($total_pages-1) ?></a></li>
                                <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo ($total_pages) ?>"><?php echo $total_pages ?></a></li>
                              <?php
                            }else {
                              ?>
                                <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=1">1</a></li>
                                <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=2">2</a></li>
                                <li><a>...</a></li>
                              <?php

                              for($i = $total_pages - 6; $i <= $total_pages; $i++){
                                if ($page == $i) {
                                  ?>
                                    <li><a  title="" class="<?php if($page == $i) echo "cl-number active"; ?>"><?php echo $i ?></a></li>
                                  <?php
                                }else {
                                  ?>
                                    <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo $i ?>" title="" ><?php echo $i ?></a></li>
                                  <?php
                                }
                              }
                            }
                          }

                          if ($page != $total_pages) {
                            ?>
                              <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo ($page+1) ?>" title=""  class="cl-square square-right"><i class="fa fa-angle-right"></i></a></li>
                              <li><a href="search.php?tukhoa=<?php echo $tukhoa ?>&page=<?php echo ($total_pages) ?>" title=""  class="cl-square square-right"><i class="fa fa-angle-right"></i><i class="fa fa-angle-right"></i></a></li>
                            <?php
                          }
                        }
                      ?>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
	</div>
<?php
	include 'public/inc/footer.php';
?>