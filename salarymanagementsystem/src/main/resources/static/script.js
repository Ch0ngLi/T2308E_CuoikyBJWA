// Confirm delete action
function confirmDelete() {
    return confirm("Are you sure you want to delete this employee?");
}

// AJAX Search (Optional: Requires controller adjustment to return HTML fragments)
$(document).ready(function() {
    // Intercept search form submission
    $('form[action="/search"]').submit(function(e) {
        e.preventDefault();
        const keyword = $('input[name="keyword"]').val();
        $.get('/search', { keyword: keyword }, function(data) {
            $('tbody').html($(data).find('tbody').html());
        });
    });
});