jQuery(document)
		.ready(
				function($) {
					"use strict"
					jQuery(window).on("load", function() {
						jQuery("#status").delay(1000).fadeOut();
						jQuery("#preloader").delay(1000).fadeOut("slow");
					})
					jQuery('#main-slides').slick({
						slidesToShow : 1,
						slidesToScroll : 1,
						fade : true,
						speed : 1000,
						arrows : false,
						asNavFor : '#slides-thmnail'
					});
					jQuery('#slides-thmnail').slick({
						slidesToShow : 4,
						slidesToScroll : 1,
						asNavFor : '#main-slides',
						dots : false,
						focusOnSelect : true,
						arrows : false,
						responsive : [ {
							breakpoint : 992,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 481,
							settings : {
								slidesToShow : 2,
								slidesToScroll : 1
							}
						} ]
					});

					jQuery('.prev-1').on("click", function() {
						jQuery('#slides-thmnail').slick('slickPrev');
					});

					jQuery('.next-1').on("click", function() {
						jQuery('#slides-thmnail').slick('slickNext');
					});

					jQuery('#add-banners-slider').slick({
						dots : true,
						infinite : true,
						speed : 700,
						arrows : true,
						slidesToShow : 3,
						slidesToScroll : 1,
						responsive : [ {
							breakpoint : 768,
							settings : {
								slidesToShow : 2,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 480,
							settings : {
								slidesToShow : 1,
								slidesToScroll : 1
							}
						} ]
					});
					jQuery('#matches-detail-slider').slick({
						dots : true,
						infinite : true,
						speed : 700,
						arrows : true,
						slidesToShow : 1,
						slidesToScroll : 1
					});

                     jQuery('.prev-1').on("click", function() {
						    jQuery('#slides-thmnail').slick('slickPrev');
                        });
                        
                        jQuery('.next-1').on("click", function() {
                            jQuery('#slides-thmnail').slick('slickNext');
                        });

					setTimeout(function() {
						jQuery('#latest-news-slider').slick({
							slidesToShow : 1,
							slidesToScroll : 1,
							arrows : false,
							fade : true,
							asNavFor : '#latest-news-thumb'
						});

						jQuery('#latest-news-thumb').slick({
							slidesToShow : 4,
							slidesToScroll : 1,
							asNavFor : '#latest-news-slider',
							dots : false,
							focusOnSelect : true,
							vertical : true,
							arrows : false,
							responsive : [ {
								breakpoint : 992,
								settings : {
									slidesToShow : 3,
									slidesToScroll : 1
								}
							}, {
								breakpoint : 768,
								settings : {
									slidesToShow : 2,
									slidesToScroll : 1
								}
							}, {
								breakpoint : 481,
								settings : {
									slidesToShow : 1,
									slidesToScroll : 1
								}
							} ]
						});

						jQuery('.prev').on("click", function() {
							jQuery('#latest-news-thumb').slick('slickPrev');
						});

						jQuery('.next').on("click", function() {
							jQuery('#latest-news-thumb').slick('slickNext');
						});
					}, 2000);

					jQuery('#video-slider').slick({
						dots : false,
						infinite : true,
						speed : 700,
						arrows : true,
						slidesToShow : 1,
						slidesToScroll : 1
					});
					jQuery('#team-slider').slick({
						dots : false,
						infinite : true,
						speed : 700,
						arrows : false,
						slidesToShow : 4,
						slidesToScroll : 1,
						responsive : [ {
							breakpoint : 992,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 1024,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 768,
							settings : {
								slidesToShow : 2,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 481,
							settings : {
								slidesToShow : 1,
								slidesToScroll : 1
							}
						} ]
					});
					jQuery('#product-slider').slick({
						dots : true,
						infinite : true,
						speed : 700,
						arrows : true,
						slidesToShow : 6,
						slidesToScroll : 1,
						responsive : [ {
							breakpoint : 1400,
							settings : {
								slidesToShow : 5,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 1200,
							settings : {
								slidesToShow : 4,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 991,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 768,
							settings : {
								slidesToShow : 2,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 481,
							settings : {
								slidesToShow : 1,
								slidesToScroll : 1
							}
						} ]
					});
					jQuery('#product-slider-2').slick({
						dots : true,
						infinite : true,
						speed : 700,
						arrows : true,
						slidesToShow : 4,
						slidesToScroll : 1,
						responsive : [ {
							breakpoint : 992,
							settings : {
								slidesToShow : 4,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 991,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 768,
							settings : {
								slidesToShow : 2,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 481,
							settings : {
								slidesToShow : 1,
								slidesToScroll : 1
							}
						} ]
					});
					jQuery('#brand-icons-slider').slick({
						dots : false,
						infinite : true,
						speed : 700,
						arrows : true,
						slidesToShow : 7,
						slidesToScroll : 1,
						responsive : [ {
							breakpoint : 102,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 991,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 768,
							settings : {
								slidesToShow : 4,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 480,
							settings : {
								slidesToShow : 1,
								slidesToScroll : 1
							}
						} ]
					});
					jQuery('#brand-icons-slider-2').slick({
						dots : false,
						infinite : true,
						speed : 700,
						arrows : true,
						centerMode : true,
						slidesToShow : 1,
						slidesToScroll : 1
					});
					jQuery('#ticker').slick({
						dots : false,
						infinite : true,
						speed : 700,
						arrows : true,
						vertical : true,
						slidesToShow : 1,
						slidesToScroll : 1,
						autoplay : true,
						autoplaySpeed : 3000
					});
					jQuery('#team-match-slider').slick({
						dots : false,
						infinite : true,
						speed : 700,
						arrows : true,
						slidesToShow : 1,
						slidesToScroll : 1
					});
					jQuery('#footer-product-slider').slick({
						dots : false,
						infinite : true,
						speed : 700,
						arrows : true,
						slidesToShow : 1,
						slidesToScroll : 1
					});
					jQuery('#mega-blog-slider').slick({
						dots : false,
						infinite : true,
						speed : 700,
						arrows : false,
						slidesToShow : 4,
						slidesToScroll : 1,
						responsive : [ {
							breakpoint : 102,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 991,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 600,
							settings : {
								slidesToShow : 2,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 480,
							settings : {
								slidesToShow : 1,
								slidesToScroll : 1
							}
						} ]
					});
					var carousel = jQuery("#video-gallery-slider")
							.waterwheelCarousel(
									{
										flankingItems : 3,
										movingToCenter : function($item) {
											jQuery('#callback-output').prepend(
													'movingToCenter:',
													'movedToCenter:',
													'movingFromCenter:',
													'movedFromCenter:',
													'clickedCenter:'
															+ $item.attr('id')
															+ '<br/>');
										}
									});
					jQuery('#prev').on('click', function() {
						carousel.prev();
						return false
					});
					jQuery('#next').on('click', function() {
						carousel.next();
						return false;
					});
					jQuery('#testimonial-slides').slick({
						slidesToShow : 1,
						slidesToScroll : 1,
						speed : 1000,
						arrows : false,
						asNavFor : '#testimonial-thumnail'
					});
					jQuery('#testimonial-thumnail').slick({
						slidesToShow : 3,
						slidesToScroll : 1,
						asNavFor : '#testimonial-slides',
						dots : false,
						focusOnSelect : true,
						arrows : false,
						responsive : [ {
							breakpoint : 641,
							settings : {
								slidesToShow : 5,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 481,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						} ]
					});
					jQuery('#post-slider').slick({
						slidesToShow : 1,
						slidesToScroll : 1,
						speed : 1000,
						arrows : false
					});
					jQuery('#match-detail-slider').slick({
						slidesToShow : 1,
						slidesToScroll : 1,
						speed : 1000,
						arrows : true
					});
					jQuery('#product-slides').slick({
						slidesToShow : 1,
						slidesToScroll : 1,
						arrows : false,
						asNavFor : '#product-thumnail'
					});
					jQuery('#product-thumnail').slick({
						slidesToShow : 4,
						slidesToScroll : 1,
						asNavFor : '#product-slides',
						dots : false,
						focusOnSelect : true,
						arrows : false,
						responsive : [ {
							breakpoint : 641,
							settings : {
								slidesToShow : 5,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 481,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						} ]
					});
					jQuery('#product-slider2').slick({
						dots : false,
						infinite : true,
						speed : 700,
						arrows : false,
						slidesToShow : 3,
						slidesToScroll : 1,
						responsive : [ {
							breakpoint : 102,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 991,
							settings : {
								slidesToShow : 3,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 600,
							settings : {
								slidesToShow : 2,
								slidesToScroll : 1
							}
						}, {
							breakpoint : 480,
							settings : {
								slidesToShow : 1,
								slidesToScroll : 1
							}
						} ]
					});
					jQuery('.menu-link').bigSlide();
					jQuery('#animated-slider').carousel({
						interval : 5000,
						pause : "false"
					});
					jQuery("#choses-lang").on("click", function(e) {
						e.preventDefault();
						jQuery("#language-dropdown").fadeToggle(100);
					});
					jQuery('#calendar').datepicker({
						inline : true
					});
					jQuery('.scrollup').on("click", function() {
						jQuery("html, body").animate({
							scrollTop : 0
						}, 1000);
						return false;
					});
					try {
						jQuery('#tc-counters').appear(function() {
							jQuery('.facts-number').countTo()
						});
					} catch (err) {
					}
					jQuery("#ex2").slider({});
					var wow = new WOW({
						boxClass : 'animate',
						animateClass : 'animated',
						offset : 0,
						mobile : false
					});
					wow.init();
					jQuery("#custom-map").gmap3({
						map : {
							options : {
								center : [ 46.578498, 2.457275 ],
								zoom : 5,
								scrollwheel : false
							}
						}
					});
					var setElementHeight = function() {
						var width = jQuery(window).width();
						var height = jQuery(window).height();
						jQuery('.fullscreen').css('height', (height));
					}
					jQuery(window).on("resize", function() {
						setElementHeight();
					}).resize();
					jQuery(
							'#countdown-1, #countdown-2, #countdown-3, #countdown-4, #countdown-5, #countdown-6, #comming-countdown')
							.countdown({
								date : '7/30/2017 2:17:59',
								offset : -2100,
								day : 'Day',
								days : 'Days'
							});
					jQuery(".panel-heading").addClass("collapsed");
					jQuery("a[data-rel]").each(function() {
						jQuery(this).attr("rel", jQuery(this).data("rel"));
					});
					jQuery("a[data-rel^='prettyPhoto']").prettyPhoto({
						animation_speed : 'normal',
						theme : 'dark_square',
						slideshow : 3000,
						autoplay_slideshow : false,
						social_tools : false
					});
					jQuery("a[rel^='prettyPhoto']").prettyPhoto();
				});