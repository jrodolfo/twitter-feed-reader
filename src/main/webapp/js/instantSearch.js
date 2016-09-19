var CollectionView = Backbone.View.extend({

    template: $('#template').html(),

    initialize: function() {

        this.collection = new Backbone.Collection([
            { first: 'John', last: 'Doe' },
            { first: 'Mary', last: 'Jane' },
            { first: 'Billy', last: 'Bob' },
            { first: 'Dexter', last: 'Morgan' },
            { first: 'Walter', last: 'White' },
            { first: 'Billy', last: 'Bobby' }
        ]);
        new RowView({ collection: this.collection });
        this.collection.on('add', this.addOne, this);
        // this.collection.fetch();

        this.render();
    },

    events: {
        'keyup .search': 'search',
    },

    // Returns array subset of models that match search.
    search: function(e) {

        var search = this.$('.search').val().toLowerCase();

        this.$('tbody').empty(); // is this creating ghost views?

        _.each(this.collection.filter(function(model) {
            return _.some(
                model.values(),
                function(value) {
                    return ~value.toLowerCase().indexOf(search);
                });
        }), $.proxy(this.addOne, this));
    },

    addOne: function(model) {

        // add row
        var view = new RowView({ model: model });
        this.$('tbody').append(view.render().el);
    },

    render: function() {

        // first render
        $('#insert').replaceWith(this.$el.html(this.template));
        this.collection.each(this.addOne, this);
    }
});

var RowView = Backbone.View.extend({

    tagName: 'tr',

    events: {
        // Some detail view will listen for this.
        // App.trigger('person:view', this.model);
    },

    render: function() {

        this.$el.html('<td>' + this.model.get('first') + '</td><td>' + this.model.get('last') + '</td>');
        return this;
    }
});

new CollectionView;