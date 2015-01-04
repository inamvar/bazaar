<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">


	<div class="row">
		<div class="col-sm-5">
			<div class="panel panel-default">
				<div class="panel-heading">
					<span class="glyphicon glyphicon-pencil"></span>
					<spring:message code="category.update.message" />
				</div>
				<div class="panel-body">
					<form:form method="POST" commandName="category" class="form"
						role="form"
						action="${pageContext.request.contextPath}/admin/category/update/${category.id}">
						<div class="form-group">
							<p>
								<spring:message code="category.name" />
							</p>
							<form:input class="form-control input-sm" path="name"></form:input>
							<form:errors path="name" cssClass="text text-danger" />
						</div>

						<div class="form-group">
							<p>
								<spring:message code="category.iconCss" />
							</p>
							<form:input class="form-control input-sm" path="iconCss"></form:input>
							<form:errors path="iconCss" cssClass="text text-danger" />
						</div>

						<div class="form-group">
							<input class="btn btn-danger btn-sm"
								value="<spring:message code="submit"/>" type="submit"> <a
								href="${pageContext.request.contextPath}/admin/category"
								class="btn btn-default btn-sm"><spring:message code="cancel" /></a>
						</div>


					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-7"></div>
	</div>

	<div style="direction: ltr;" class="panel panel-success">
		<div class="panel-body">
			<h3>Web Application Icons</h3>


			<div class="row">

				<div class="col-sm-2">
					<span><i class="icon-adjust"></i> icon-adjust</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-anchor"></i> icon-anchor</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-archive"></i> icon-archive</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-asterisk"></i> icon-asterisk</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-ban-circle"></i> icon-ban-circle</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bar-chart"></i> icon-bar-chart</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-barcode"></i> icon-barcode</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-beaker"></i> icon-beaker</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-beer"></i> icon-beer</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bell"></i> icon-bell</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bell-alt"></i> icon-bell-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bolt"></i> icon-bolt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-book"></i> icon-book</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bookmark"></i> icon-bookmark</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bookmark-empty"></i>
						icon-bookmark-empty</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-briefcase"></i> icon-briefcase</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bug"></i> icon-bug</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-building"></i> icon-building</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bullhorn"></i> icon-bullhorn</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bullseye"></i> icon-bullseye</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-calendar"></i> icon-calendar</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-calendar-empty"></i>
						icon-calendar-empty</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-camera"></i> icon-camera</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-camera-retro"></i> icon-camera-retro</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-certificate"></i> icon-certificate</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-check"></i> icon-check</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-check-empty"></i> icon-check-empty</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-check-minus"></i> icon-check-minus</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-check-sign"></i> icon-check-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-circle"></i> icon-circle</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-circle-blank"></i> icon-circle-blank</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-cloud"></i> icon-cloud</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-cloud-download"></i>
						icon-cloud-download</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-cloud-upload"></i> icon-cloud-upload</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-code"></i> icon-code</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-code-fork"></i> icon-code-fork</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-coffee"></i> icon-coffee</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-cog"></i> icon-cog</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-cogs"></i> icon-cogs</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-collapse"></i> icon-collapse</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-collapse-alt"></i> icon-collapse-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-collapse-top"></i> icon-collapse-top</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-comment"></i> icon-comment</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-comment-alt"></i> icon-comment-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-comments"></i> icon-comments</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-comments-alt"></i> icon-comments-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-compass"></i> icon-compass</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-credit-card"></i> icon-credit-card</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-crop"></i> icon-crop</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-dashboard"></i> icon-dashboard</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-desktop"></i> icon-desktop</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-download"></i> icon-download</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-download-alt"></i> icon-download-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-edit"></i> icon-edit</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-edit-sign"></i> icon-edit-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-ellipsis-horizontal"></i>
						icon-ellipsis-horizontal</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-ellipsis-vertical"></i>
						icon-ellipsis-vertical</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-envelope"></i> icon-envelope</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-envelope-alt"></i> icon-envelope-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-eraser"></i> icon-eraser</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-exchange"></i> icon-exchange</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-exclamation"></i> icon-exclamation</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-exclamation-sign"></i>
						icon-exclamation-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-expand"></i> icon-expand</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-expand-alt"></i> icon-expand-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-external-link"></i> icon-external-link</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-external-link-sign"></i>
						icon-external-link-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-eye-close"></i> icon-eye-close</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-eye-open"></i> icon-eye-open</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-facetime-video"></i>
						icon-facetime-video</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-female"></i> icon-female</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-fighter-jet"></i> icon-fighter-jet</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-film"></i> icon-film</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-filter"></i> icon-filter</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-fire"></i> icon-fire</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-fire-extinguisher"></i>
						icon-fire-extinguisher</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-flag"></i> icon-flag</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-flag-alt"></i> icon-flag-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-flag-checkered"></i>
						icon-flag-checkered</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-folder-close"></i> icon-folder-close</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-folder-close-alt"></i>
						icon-folder-close-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-folder-open"></i> icon-folder-open</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-folder-open-alt"></i>
						icon-folder-open-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-food"></i> icon-food</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-frown"></i> icon-frown</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-gamepad"></i> icon-gamepad</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-gear"></i> icon-gear <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-gears"></i> icon-gears <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-gift"></i> icon-gift</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-glass"></i> icon-glass</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-globe"></i> icon-globe</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-group"></i> icon-group</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-hdd"></i> icon-hdd</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-headphones"></i> icon-headphones</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-heart"></i> icon-heart</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-heart-empty"></i> icon-heart-empty</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-home"></i> icon-home</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-inbox"></i> icon-inbox</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-info"></i> icon-info</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-info-sign"></i> icon-info-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-key"></i> icon-key</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-keyboard"></i> icon-keyboard</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-laptop"></i> icon-laptop</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-leaf"></i> icon-leaf</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-legal"></i> icon-legal</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-lemon"></i> icon-lemon</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-level-down"></i> icon-level-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-level-up"></i> icon-level-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-lightbulb"></i> icon-lightbulb</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-location-arrow"></i>
						icon-location-arrow</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-lock"></i> icon-lock</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-magic"></i> icon-magic</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-magnet"></i> icon-magnet</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-mail-forward"></i> icon-mail-forward <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-mail-reply"></i> icon-mail-reply <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-mail-reply-all"></i>
						icon-mail-reply-all</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-male"></i> icon-male</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-map-marker"></i> icon-map-marker</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-meh"></i> icon-meh</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-microphone"></i> icon-microphone</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-microphone-off"></i>
						icon-microphone-off</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-minus"></i> icon-minus</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-minus-sign"></i> icon-minus-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-minus-sign-alt"></i>
						icon-minus-sign-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-mobile-phone"></i> icon-mobile-phone</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-money"></i> icon-money</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-moon"></i> icon-moon</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-move"></i> icon-move</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-music"></i> icon-music</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-off"></i> icon-off</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-ok"></i> icon-ok</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-ok-circle"></i> icon-ok-circle</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-ok-sign"></i> icon-ok-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-pencil"></i> icon-pencil</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-phone"></i> icon-phone</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-phone-sign"></i> icon-phone-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-picture"></i> icon-picture</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-plane"></i> icon-plane</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-plus"></i> icon-plus</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-plus-sign"></i> icon-plus-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-plus-sign-alt"></i> icon-plus-sign-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-power-off"></i> icon-power-off <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-print"></i> icon-print</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-pushpin"></i> icon-pushpin</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-puzzle-piece"></i> icon-puzzle-piece</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-qrcode"></i> icon-qrcode</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-question"></i> icon-question</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-question-sign"></i> icon-question-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-quote-left"></i> icon-quote-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-quote-right"></i> icon-quote-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-random"></i> icon-random</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-refresh"></i> icon-refresh</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-remove"></i> icon-remove</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-remove-circle"></i> icon-remove-circle</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-remove-sign"></i> icon-remove-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-reorder"></i> icon-reorder</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-reply"></i> icon-reply</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-reply-all"></i> icon-reply-all</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-resize-horizontal"></i>
						icon-resize-horizontal</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-resize-vertical"></i>
						icon-resize-vertical</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-retweet"></i> icon-retweet</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-road"></i> icon-road</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-rocket"></i> icon-rocket</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-rss"></i> icon-rss</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-rss-sign"></i> icon-rss-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-screenshot"></i> icon-screenshot</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-search"></i> icon-search</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-share"></i> icon-share</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-share-alt"></i> icon-share-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-share-sign"></i> icon-share-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-shield"></i> icon-shield</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-shopping-cart"></i> icon-shopping-cart</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sign-blank"></i> icon-sign-blank</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-signal"></i> icon-signal</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-signin"></i> icon-signin</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-signout"></i> icon-signout</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sitemap"></i> icon-sitemap</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-smile"></i> icon-smile</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sort"></i> icon-sort</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sort-by-alphabet"></i>
						icon-sort-by-alphabet</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sort-by-alphabet-alt"></i>
						icon-sort-by-alphabet-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sort-by-attributes"></i>
						icon-sort-by-attributes</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sort-by-attributes-alt"></i>
						icon-sort-by-attributes-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sort-by-order"></i> icon-sort-by-order</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sort-by-order-alt"></i>
						icon-sort-by-order-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sort-down"></i> icon-sort-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sort-up"></i> icon-sort-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-spinner"></i> icon-spinner</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-star"></i> icon-star</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-star-empty"></i> icon-star-empty</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-star-half"></i> icon-star-half</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-star-half-empty"></i>
						icon-star-half-empty</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-star-half-full"></i>
						icon-star-half-full <span class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-subscript"></i> icon-subscript</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-suitcase"></i> icon-suitcase</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-sun"></i> icon-sun</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-superscript"></i> icon-superscript</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-tablet"></i> icon-tablet</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-tag"></i> icon-tag</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-tags"></i> icon-tags</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-tasks"></i> icon-tasks</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-terminal"></i> icon-terminal</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-thumbs-down"></i> icon-thumbs-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-thumbs-down-alt"></i>
						icon-thumbs-down-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-thumbs-up"></i> icon-thumbs-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-thumbs-up-alt"></i> icon-thumbs-up-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-ticket"></i> icon-ticket</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-time"></i> icon-time</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-tint"></i> icon-tint</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-trash"></i> icon-trash</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-trophy"></i> icon-trophy</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-truck"></i> icon-truck</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-umbrella"></i> icon-umbrella</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-unchecked"></i> icon-unchecked <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-unlock"></i> icon-unlock</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-unlock-alt"></i> icon-unlock-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-upload"></i> icon-upload</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-upload-alt"></i> icon-upload-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-user"></i> icon-user</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-volume-down"></i> icon-volume-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-volume-off"></i> icon-volume-off</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-volume-up"></i> icon-volume-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-warning-sign"></i> icon-warning-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-wrench"></i> icon-wrench</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-zoom-in"></i> icon-zoom-in</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-zoom-out"></i> icon-zoom-out</span>
				</div>

			</div>




			<div class="gap gap-small"></div>
			<h3>Currency Icons</h3>

			<div class="row the-icons">



				<div class="col-sm-2">
					<span><i class="icon-bitcoin"></i> icon-bitcoin <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-btc"></i> icon-btc</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-cny"></i> icon-cny</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-dollar"></i> icon-dollar <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-eur"></i> icon-eur</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-euro"></i> icon-euro <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-gbp"></i> icon-gbp</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-inr"></i> icon-inr</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-jpy"></i> icon-jpy</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-krw"></i> icon-krw</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-renminbi"></i> icon-renminbi <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-rupee"></i> icon-rupee <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-usd"></i> icon-usd</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-won"></i> icon-won <span class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-yen"></i> icon-yen <span class="muted">(alias)</span></span>
				</div>

			</div>




			<div class="gap gap-small"></div>
			<h3>Text Editor Icons</h3>

			<div class="row the-icons">



				<div class="col-sm-2">
					<span><i class="icon-align-center"></i> icon-align-center</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-align-justify"></i> icon-align-justify</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-align-left"></i> icon-align-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-align-right"></i> icon-align-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bold"></i> icon-bold</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-columns"></i> icon-columns</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-copy"></i> icon-copy</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-cut"></i> icon-cut</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-eraser"></i> icon-eraser</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-file"></i> icon-file</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-file-alt"></i> icon-file-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-file-text"></i> icon-file-text</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-file-text-alt"></i> icon-file-text-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-font"></i> icon-font</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-indent-left"></i> icon-indent-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-indent-right"></i> icon-indent-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-italic"></i> icon-italic</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-link"></i> icon-link</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-list"></i> icon-list</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-list-alt"></i> icon-list-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-list-ol"></i> icon-list-ol</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-list-ul"></i> icon-list-ul</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-paper-clip"></i> icon-paper-clip</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-paperclip"></i> icon-paperclip <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-paste"></i> icon-paste</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-repeat"></i> icon-repeat</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-rotate-left"></i> icon-rotate-left <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-rotate-right"></i> icon-rotate-right <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-save"></i> icon-save</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-strikethrough"></i> icon-strikethrough</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-table"></i> icon-table</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-text-height"></i> icon-text-height</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-text-width"></i> icon-text-width</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-th"></i> icon-th</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-th-large"></i> icon-th-large</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-th-list"></i> icon-th-list</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-underline"></i> icon-underline</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-undo"></i> icon-undo</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-unlink"></i> icon-unlink</span>
				</div>

			</div>




			<div class="gap gap-small"></div>
			<h3>Directional Icons</h3>

			<div class="row the-icons">



				<div class="col-sm-2">
					<span><i class="icon-angle-down"></i> icon-angle-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-angle-left"></i> icon-angle-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-angle-right"></i> icon-angle-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-angle-up"></i> icon-angle-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-arrow-down"></i> icon-arrow-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-arrow-left"></i> icon-arrow-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-arrow-right"></i> icon-arrow-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-arrow-up"></i> icon-arrow-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-caret-down"></i> icon-caret-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-caret-left"></i> icon-caret-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-caret-right"></i> icon-caret-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-caret-up"></i> icon-caret-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-chevron-down"></i> icon-chevron-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-chevron-left"></i> icon-chevron-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-chevron-right"></i> icon-chevron-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-chevron-sign-down"></i>
						icon-chevron-sign-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-chevron-sign-left"></i>
						icon-chevron-sign-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-chevron-sign-right"></i>
						icon-chevron-sign-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-chevron-sign-up"></i>
						icon-chevron-sign-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-chevron-up"></i> icon-chevron-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-circle-arrow-down"></i>
						icon-circle-arrow-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-circle-arrow-left"></i>
						icon-circle-arrow-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-circle-arrow-right"></i>
						icon-circle-arrow-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-circle-arrow-up"></i>
						icon-circle-arrow-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-double-angle-down"></i>
						icon-double-angle-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-double-angle-left"></i>
						icon-double-angle-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-double-angle-right"></i>
						icon-double-angle-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-double-angle-up"></i>
						icon-double-angle-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-hand-down"></i> icon-hand-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-hand-left"></i> icon-hand-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-hand-right"></i> icon-hand-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-hand-up"></i> icon-hand-up</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-long-arrow-down"></i>
						icon-long-arrow-down</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-long-arrow-left"></i>
						icon-long-arrow-left</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-long-arrow-right"></i>
						icon-long-arrow-right</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-long-arrow-up"></i> icon-long-arrow-up</span>
				</div>

			</div>




			<div class="gap gap-small"></div>
			<h3>Video Player Icons</h3>

			<div class="row the-icons">



				<div class="col-sm-2">
					<span><i class="icon-backward"></i> icon-backward</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-eject"></i> icon-eject</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-fast-backward"></i> icon-fast-backward</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-fast-forward"></i> icon-fast-forward</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-forward"></i> icon-forward</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-fullscreen"></i> icon-fullscreen</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-pause"></i> icon-pause</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-play"></i> icon-play</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-play-circle"></i> icon-play-circle</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-play-sign"></i> icon-play-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-resize-full"></i> icon-resize-full</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-resize-small"></i> icon-resize-small</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-step-backward"></i> icon-step-backward</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-step-forward"></i> icon-step-forward</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-stop"></i> icon-stop</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-youtube-play"></i> icon-youtube-play</span>
				</div>

			</div>




			<div class="gap gap-small"></div>
			<h3>Brand Icons</h3>

			<div class="alert alert-info">
				<ul class="margin-bottom-none">
					<li>All brand icons are trademarks of their respective owners.</li>
					<li>The use of these trademarks does not indicate endorsement
						of the trademark holder by Font Awesome, nor vice versa.</li>
				</ul>

			</div>

			<div class="row the-icons">



				<div class="col-sm-2">
					<span><i class="icon-adn"></i> icon-adn</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-android"></i> icon-android</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-apple"></i> icon-apple</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bitbucket"></i> icon-bitbucket</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bitbucket-sign"></i>
						icon-bitbucket-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-bitcoin"></i> icon-bitcoin <span
						class="muted">(alias)</span></span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-btc"></i> icon-btc</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-css3"></i> icon-css3</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-dribbble"></i> icon-dribbble</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-dropbox"></i> icon-dropbox</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-facebook"></i> icon-facebook</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-facebook-sign"></i> icon-facebook-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-flickr"></i> icon-flickr</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-foursquare"></i> icon-foursquare</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-github"></i> icon-github</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-github-alt"></i> icon-github-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-github-sign"></i> icon-github-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-gittip"></i> icon-gittip</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-google-plus"></i> icon-google-plus</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-google-plus-sign"></i>
						icon-google-plus-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-html5"></i> icon-html5</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-instagram"></i> icon-instagram</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-linkedin"></i> icon-linkedin</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-linkedin-sign"></i> icon-linkedin-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-linux"></i> icon-linux</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-maxcdn"></i> icon-maxcdn</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-pinterest"></i> icon-pinterest</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-pinterest-sign"></i>
						icon-pinterest-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-renren"></i> icon-renren</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-skype"></i> icon-skype</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-stackexchange"></i> icon-stackexchange</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-trello"></i> icon-trello</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-tumblr"></i> icon-tumblr</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-tumblr-sign"></i> icon-tumblr-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-twitter"></i> icon-twitter</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-twitter-sign"></i> icon-twitter-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-vk"></i> icon-vk</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-weibo"></i> icon-weibo</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-windows"></i> icon-windows</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-xing"></i> icon-xing</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-xing-sign"></i> icon-xing-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-youtube"></i> icon-youtube</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-youtube-play"></i> icon-youtube-play</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-youtube-sign"></i> icon-youtube-sign</span>
				</div>

			</div>



			<div class="gap gap-small"></div>
			<h3>Medical Icons</h3>

			<div class="row the-icons">



				<div class="col-sm-2">
					<span><i class="icon-ambulance"></i> icon-ambulance</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-h-sign"></i> icon-h-sign</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-hospital"></i> icon-hospital</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-medkit"></i> icon-medkit</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-plus-sign-alt"></i> icon-plus-sign-alt</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-stethoscope"></i> icon-stethoscope</span>
				</div>

				<div class="col-sm-2">
					<span><i class="icon-user-md"></i> icon-user-md</span>
				</div>
			</div>
		</div>
	</div>

</div>

<style>
i {
	font-size: 1.6em;
}
</style>

