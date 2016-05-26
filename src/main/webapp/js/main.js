(function ($) {

    // This code is based on the code took from Chapter 7 (REST and Storage)
    // from the book: Backbone.js Cookbook, by Mirgorod, Vadim. Packt Publishing.

        // Extend Backbone Model to support MongoDB Extended JSON.
    Backbone.Model.prototype.parse = function (resp, options) {
        if (_.isObject(resp._id)) {
            resp.id = resp._id.$oid;
            delete resp._id;
        }
        return resp;
    }
    Backbone.Model.prototype.toJSON = function () {
        var attrs = _.omit(this.attributes, 'id');
        if (!_.isUndefined(this.id)) {
            attrs._id = {$oid: this.id};
        }
        return attrs;
    }

    // Define configuration.
    var appConfig = {
        baseURL: 'http://localhost:8080/tweets'
    }

    // Create Polling collection
    var PollingCollection = Backbone.Collection.extend({
        polling: false,

        // Set default interval in seconds
        interval: 60,

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
    });

    // Define tweet model
    var TweetModel = Backbone.Model.extend({
        url: function () {
                return appConfig.baseURL;
        },
    });


    // Define tweet collection.
    var TweetCollection = PollingCollection.extend({
        model: TweetModel,
        url: function () {
            return appConfig.baseURL;
        },
    });


    // Define new view to render a model.
    var TweetView = Backbone.View.extend({

        // Define element tag name.
        tagName: 'li',

        // Define template.
        template: _.template('<b>User name:</b> <%= userName %> <br /> <b>User Screen Name:</b> <%= userScreenName %><br /><b>User profile image URL:</b> <%= userProfileImage %><br /><b>Content:</b> <%= tweetContent %><br /><b>Retweet count:</b> <%= retweetCount %><br /><b>Created at:</b> <%= createdAtString %><br />'),

        // Render view.
        render: function () {
            $(this.el).html(this.template(this.model.toJSON()));

            return this;
        },

        // Bind callback to the model events.
        initialize: function () {
            this.listenTo(this.model, 'change', this.render, this);
            this.listenTo(this.model, 'destroy', this.remove, this);
        }
    });


    // Define new view to render a collection.
    var TweetListView = Backbone.View.extend({

        // Define element tag name.
        tagName: 'ol type="1"',

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
    });


    $(document).ready(function () {

        // Create collection
        collection = new TweetCollection();

        // Create whole page view instance and render it
        $('body').append('<h3>Tweets</h3>')
        $('body').append(new TweetListView({
            collection: collection,
        }).render().el);

        collection.startPolling();
    });

})(jQuery);
