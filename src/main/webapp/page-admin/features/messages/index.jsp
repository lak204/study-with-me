<div class="row" id="posts-table">
  <div class="col-12">
    <div class="card card-primary">
      <div class="card-header">
        <h3 class="card-title">Messages</h3>
      </div>
      <div class="row p-2">
        <div class="col-6">
          <div class="btn-group">
            
          </div>
        </div>
        <div class="col-6">
          <div class="input-group input-group-sm float-right" style="width: 250px">
            <input type="text" name="table_search" class="form-control float-right" placeholder="Search" id=inpSearchPostsName oninput="onSearchByName()">
            <div class="input-group-append">
              <button type="submit" class="btn btn-default">
                <i class="fas fa-search"></i>
              </button>
            </div>
          </div>
        </div>
      </div>
      <div class="card-body table-responsive p-0">
        <table class="table table-head-fixed text-wrap table-sm table-striped">
          <thead>
            <tr>
              <th>Id</th>
              <th>Email</th>
            <th>Subject</th>
             <th>Message</th>
              <th>Created Date</th>
              
              <th style="width: 95px;">Actions</th>
            </tr>
          </thead>
          <tbody id="tblPosts">
          </tbody>
        </table>
        <div class="card-footer clearfix">
          <ul id="postsPagination" class="pagination-sm float-right"></ul>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row" id="posts-form">
  <div class="col-12">
    <div class="card card-primary">
      <div class="card-header">
        <h3 class="card-title">Create/Edit Posts</h3>
      </div>
      <!-- /.card-header -->
      <div class="card-body">
        <div class="form-group" style="display: none">
          <label for="name">Id</label> <input type="text" name="id" class="form-control" id="inpPostsId">
        </div>
        <div class="form-group">
          <label for="name">Subject</label> <input class="form-control" placeholder="Subject" id="inpPostsTitle">
        </div>
          <!-- Start Edit custom form -->
        
        <div class="form-group">
          <label for="name">Email</label> <input class="form-control" placeholder="Email" id="inpEmailTitle">
        </div>
        <div class="form-group">
          <label for="name">Message</label> <input class="form-control" placeholder="Message" id="inpMessageTitle">
        </div>
        <!-- End Edit custom form -->
       
       
       
      </div>
      <!-- /.card-body -->
      <div class="card-footer">
        <div class="float-right">
          <button type="button" class="btn btn-default" onclick="draftPosts()">
            <i class="fas fa-pencil-alt"></i> Draft
          </button>
          <button type="button" class="btn btn-primary" onclick="savePosts()">
            <i class="fas fa-save"></i> Save
          </button>
        </div>
        <button type="button" class="btn btn-default" onclick="swicthViewPosts(true)">
          <i class="fas fa-times"></i> Discard
        </button>
      </div>
      <!-- /.card-footer -->
    </div>
    <!-- /.card -->
  </div>
</div>
<script src="${pageContext.request.contextPath}/page-admin/features/messages/index.js"></script>