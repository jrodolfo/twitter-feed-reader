(function ($) {

    // This code is based on the code took from Chapter 7 (REST and Storage)
    // from the book: Backbone.js Cookbook, by Mirgorod, Vadim. Packt Publishing.

    // Define configuration.
    var appConfig = {
        baseURL: '/tweets'
    }

    // Create Polling Collection - start
    var PollingCollection = Backbone.Collection.extend({
        polling: false,

        // Set default interval in seconds
        // we want the page to be updated every minute. It takes up to 1 second
        // to get the response from the servlet. So we set interval equals to 59
        interval: 59,

        // Make all object methods to work from it's own context.
        initialize: function() {
            _.bindAll(this);
        },

        // Starts polling.
        startPolling: function (interval) {
            this.polling = true;

            if (interval) {
                this.interval = interval;
            }

            this.executePolling();
        },

        // Stops polling.
        stopPolling: function () {
            this.polling = false;
        },

        // Execute polling
        executePolling: function () {
            this.fetch({success: this.onFetch, error: this.onFetch});
        },

        // Runs recursion
        onFetch: function () {
            setTimeout(this.executePolling, 1000 * this.interval)
        },
    }); // Create Polling Collection - end

    // Define Tweet Model - start
    var TweetModel = Backbone.Model.extend({
        url: function () {
                return appConfig.baseURL;
        },
    });// Define Tweet Model - end


    // Define Tweet Collection - start
    var TweetCollection = PollingCollection.extend({
        model: TweetModel,
        url: function () {
            return appConfig.baseURL;
        },
    });  // Define Tweet Collection - end

    // Define Tweet Template - start
    var TweetTemplate = {
        getTemplate: function () {
            var userProfileImage = '<img src="<%= userProfileImageURL %>" class="image" />';
            var ulOpen = '<ul>';
            var ulClose = "</ul>";
            var liOpen =  '<li class="tweetInfo">';
            var liClose = '</li>';
            var userName = '<a target="_blank" href="https://twitter.com/<%= userScreenName %>"> <%= userName %></a>';
            var userScreenName = '@<%= userScreenName %>';
            var createdAt = '<%= createdAt %>';
            var header = liOpen + userName + " " + userScreenName + " - " + createdAt + liClose;
            var content = liOpen + '<%= tweetContent %>' + liClose;
            var retweetCount = liOpen + 'Retweet: <%= retweetCount %>' + liClose;
            return userProfileImage + ulOpen + header + content + retweetCount + ulClose;
        }
    } // Define Tweet Template - end

    // Define Tweet View to render a model - start
    var TweetView = Backbone.View.extend({

        tagName: "article",
        className: "tweetListItem",
        template: _.template(TweetTemplate.getTemplate()),

        // Render view.
        render: function () {
            $(this.el).html(this.template(this.model.toJSON()));
            return this;
        },

        events: {
            'mouseover': 'addBgColor',
            'mouseout': 'removeBgColor'
        },

        addBgColor: function() {
            this.$el.addClass("bgColorImage");
        },

        removeBgColor: function() {
            this.$el.removeClass("bgColorImage");
        },


        // Bind callback to the model events.
        initialize: function () {
            this.listenTo(this.model, 'change', this.render, this);
            this.listenTo(this.model, 'destroy', this.remove, this);
        }
    }); // Define Tweet View to render a model - end


    // Define Tweet List View to render a collection - start
    var TweetListView = Backbone.View.extend({

        // Define element tag name.
        tagName: "section",

        // Render view.
        render: function () {
            $(this.el).empty();

            // Append table with a row.
            _.each(this.collection.models, function (model, key) {
                this.append(model);
            }, this);

            return this;
        },

        // Add tweet item row to the table.
        append: function (model) {
            $(this.el).append(
                new TweetView({model: model}).render().el
            );
        },

        // Remove model from collection.
        remove: function (model) {
            model.trigger('destroy');
        },

        // Bind callbacks to the collection events.
        initialize: function () {
            this.listenTo(this.collection, 'reset', this.render, this);
            this.listenTo(this.collection, 'add', this.append, this);
            this.listenTo(this.collection, 'remove', this.remove, this);
        },
    });// Define Tweet List View to render a collection - end


    $(document).ready(function () {

        // Create collection
        collection = new TweetCollection();

        // Create whole page view instance and render it
        $('#allTweets').append(new TweetListView({
            collection: collection,
        }).render().el);

        collection.startPolling();
    });

})(jQuery);
