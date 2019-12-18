app.controller('brandController',function($scope,$controller,brandService){
	$controller('baseController',{$scope:$scope});//继承	
    	//刷新列表 
    	$scope.reloadList=function(){
    		//切换页码
    		  $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);				
    		  $scope.selectIds=[];//选中的ID集合   每次切换页面置为空
    		  
    	}
    	//分页控件配置
    	$scope.paginationConf = {
    			 currentPage: 1,
    			 totalItems: 10,
    			 itemsPerPage: 5,
    			 perPageOptions: [10, 20, 30, 40, 50],
    			 onChange: function(){
    			       $scope.reloadList();//重新加载
    			 }
    	}; 
    	//分页
    	
    	$scope.findPage=function(page,rows){	
    		brandService.findPage(page,rows).success(
    				function(response){
    					$scope.list=response.rows;	
    					$scope.paginationConf.totalItems=response.total;//更新总记录数
    				}
    		);
    	}
    	//保存  
    	$scope.save=function(){
    			if($scope.brand.id!=null){//如果有ID
    				//则执行修改方法 
    				brandService.update($scope.brand).success(
    	    				function(result){
    	    						
    	   						if(result.success){
    	   							   //重新查询 
    	   					            $scope.reloadList();//重新加载
    	   						}else{
    	   							   
    	   						 }
    	   						alert(result.message);
    	   					}
    	    			);				
    			}
    			brandService.add($scope.brand).success(
    				function(result){
    						
   						if(result.success){
   							   //重新查询 
   					            $scope.reloadList();//重新加载
   						}else{
   							   
   						 }
   						alert(result.message);
   					}
    			);				
    	}
    	//查询实体 
    	$scope.findById=function(id){
    		brandService.findById(id).success(
    				function(response){
    					$scope.brand= response;					
    			     }
    		);
    	}
    	
    	
    	//更新复选
    	$scope.updateSelection = function($event, id) {		
    			if($event.target.checked){//如果是被选中,则增加到数组
    					$scope.selectIds.push(id);			
    			}else{
    					var idx = $scope.selectIds.indexOf(id);
    			         $scope.selectIds.splice(idx, 1);//删除 
    			}
    			console.log($scope.selectIds)
    	}			 
    	//批量删除 
    	$scope.dele=function(){			
    			//获取选中的复选框			
    			brandService.dele($scope.selectIds).success(
    					function(result){
    						alert(result.message)
    						if(result.success){
    								$scope.reloadList();//刷新列表
    						}						
    					}		
    			);				
    	}
    	$scope.searchEntity={};//定义搜索对象 			
    	//条件查询 
    	$scope.search=function(page,rows){
    		brandService.search(page,rows,$scope.searchEntity).success(
    			function(response){
    					$scope.paginationConf.totalItems=response.total;//总记录数 
    					$scope.list=response.rows;//给列表变量赋值 
    			}		
    		);				
    	}
    })